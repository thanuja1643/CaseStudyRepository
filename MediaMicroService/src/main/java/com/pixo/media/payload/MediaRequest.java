package com.pixo.media.payload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public class MediaRequest {
	
	private String userId;

	private String mediaTitle;
	private String mediaCaption;
	


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	
	

}
