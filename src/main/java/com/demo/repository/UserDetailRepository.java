package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.model.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>{
	
	 @Query("SELECT u FROM UserDetail u WHERE u.username= :username")
	 UserDetail findByUsername(@Param("username") String username);
	
	 @Query("SELECT id FROM UserDetail u WHERE u.username= :username")
	 Integer findByUsernameId(@Param("username") String username);

}
