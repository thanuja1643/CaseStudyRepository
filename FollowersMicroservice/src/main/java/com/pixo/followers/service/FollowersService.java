package com.pixo.followers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;


import com.pixo.followers.dto.UserDetail;
import com.pixo.followers.entities.FollowerDetails;
import com.pixo.followers.repository.FollowersRepository;

@Service
public class FollowersService {
	@Autowired
	FollowersRepository followersrepo;
	
	@Autowired
	private RestTemplate restTemplate;

	public List<UserDetail> getFollowersOfuser(Long uid){
		
		Optional<FollowerDetails> followers = followersrepo.findByUserId(uid);
		List<String> fids =followers.get().getFollowersId();
			List<UserDetail> fuserdet = (List<UserDetail>) restTemplate.getForEntity("http:localhost:8182/user/{id}", List.class);
		
		return fuserdet;
		
	}

}
