package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Advertisement;

@Repository
public interface AdvertisementRepository  extends JpaRepository<Advertisement, Integer> {
	
	@Query("SELECT a FROM Advertisement a WHERE a.name= :name ")
	Advertisement findByName(@Param("name") String name);

}
