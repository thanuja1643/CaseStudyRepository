package com.pixo.followers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pixo.followers.dto.UserDetail;
import com.pixo.followers.service.FollowersService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController("pixofollower")
public class FollowerController {
	
	@Autowired
	FollowersService followersService;
	
	
	@GetMapping("followers/{id}")
	ResponseEntity<?> getFollowersOfuser(@PathVariable Long id){
		List<UserDetail> followersDetList =followersService.getFollowersOfuser(id);
		
		return ResponseEntity.ok(followersDetList);
		
	}
	

}
