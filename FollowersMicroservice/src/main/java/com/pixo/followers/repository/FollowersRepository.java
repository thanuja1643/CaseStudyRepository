package com.pixo.followers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixo.followers.entities.FollowerDetails;

public interface FollowersRepository extends JpaRepository<FollowerDetails, Long>{

	Optional<FollowerDetails> findByUserId(Long id);

}
