package com.selvam.dreamshops.repository;

import com.selvam.dreamshops.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
