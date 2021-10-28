package com.pixo.followers.entities;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "followerDetails")
@Data
public class FollowerDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@ElementCollection
	@CollectionTable(name="followersUsers",
	joinColumns = @JoinColumn(name="userId"))
	private List<String> followersId;


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	

	public List<String> getFollowersId() {
		return followersId;
	}


	public void setFollowersId(List<String> followersId) {
		this.followersId = followersId;
	}


	public FollowerDetails() {
		super();
	}
	
	

}
