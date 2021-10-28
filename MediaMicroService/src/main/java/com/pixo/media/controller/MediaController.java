package com.pixo.media.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixo.media.model.MediaInfo;
import com.pixo.media.payload.MediaRequest;
import com.pixo.media.payload.MediaUploadResponse;
import com.pixo.media.repository.MediaRepository;
import com.pixo.media.service.MediaService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController("media")
public class MediaController {

    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    private MediaService fileStorageService;
    @Autowired
    private MediaRepository mediaRepo;
   
    @RequestMapping(value = "/singleFileUpload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public MediaUploadResponse uploadFileWithData(@RequestPart("medReq") String medReq, @RequestPart("file") MultipartFile file) {
    	
    	MediaRequest singleMediaRequestEntity = null;
		try {
			singleMediaRequestEntity = new ObjectMapper().readValue(medReq,
					MediaRequest.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String fileName = fileStorageService.storeFile(file);
        MediaInfo m = new MediaInfo();
        m.setMediaTitle(singleMediaRequestEntity.getMediaTitle());
        m.setMediaCaption(singleMediaRequestEntity.getMediaCaption());
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        m.setUserId(singleMediaRequestEntity.getUserId());
        m.setMediaurl(fileDownloadUri);
        mediaRepo.save(m);

        return new MediaUploadResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<List<MediaUploadResponse>> uploadMultipleFiles(@RequestPart("medReq") String medReq,@RequestParam("files") MultipartFile[] files) {
    	MediaRequest MultiMediaRequestEntity = null;
		try {
			MultiMediaRequestEntity = new ObjectMapper().readValue(medReq,
					MediaRequest.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MediaUploadResponse> filesList = new ArrayList<MediaUploadResponse>();
		
        Arrays.asList(files)
                .stream()
                .forEach(file->{
                	String cfileName = fileStorageService.storeFile(file);
                	 String cfileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                             .path("/downloadFile/")
                             .path(cfileName)
                             .toUriString();
                	filesList.add(new MediaUploadResponse(cfileName, cfileDownloadUri,
                            file.getContentType(), file.getSize()));
                });
                
        
        return new ResponseEntity(filesList,HttpStatus.OK);
    }

    @CircuitBreaker(name = "downloadfall", fallbackMethod = "dummyFallbackmethod")
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
    public ResponseEntity<?> dummyFallbackmethod(){
    	return ResponseEntity.ok("could not load images now.Please wait....");
    }
}