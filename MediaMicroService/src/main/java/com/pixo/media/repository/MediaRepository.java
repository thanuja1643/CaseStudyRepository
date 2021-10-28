package com.pixo.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pixo.media.model.MediaInfo;

public interface MediaRepository extends JpaRepository<MediaInfo, Long>{

}
