package com.pixo.newsfeed.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "newsfeed")
@Data
public class NewsFeed {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long postId;
	private String postName;
	private String postDescription;
	private String postComments;
	private Long userId;
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostDescription() {
		return postDescription;
	}
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}
	public String getPostComments() {
		return postComments;
	}
	public void setPostComments(String postComments) {
		this.postComments = postComments;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public NewsFeed() {
		super();
	}
	
	
	
	
	

}
