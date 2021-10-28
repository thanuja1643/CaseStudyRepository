package com.pixo.media.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Entity
@Table(name= "mediaInfo")
@Data
public class MediaInfo implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long mediaId;

private String imageName;

private String mediaurl;

private String mediaTitle;
private String mediaCaption;
@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
private Date uploadedDateTime;
private String userId;
public Long getMediaId() {
	return mediaId;
}
public void setMediaId(Long mediaId) {
	this.mediaId = mediaId;
}
public String getImageName() {
	return imageName;
}
public void setImageName(String imageName) {
	this.imageName = imageName;
}
public String getMediaurl() {
	return mediaurl;
}
public void setMediaurl(String mediaurl) {
	this.mediaurl = mediaurl;
}

public String getMediaTitle() {
	return mediaTitle;
}
public void setMediaTitle(String mediaTitle) {
	this.mediaTitle = mediaTitle;
}
public String getMediaCaption() {
	return mediaCaption;
}
public void setMediaCaption(String mediaCaption) {
	this.mediaCaption = mediaCaption;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}

public Date getUploadedDateTime() {
	return uploadedDateTime;
}
public void setUploadedDateTime(Date uploadedDateTime) {
	this.uploadedDateTime = uploadedDateTime;
}
public MediaInfo() {
	super();
}


}