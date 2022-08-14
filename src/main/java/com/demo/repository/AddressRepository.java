package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	@Query("SELECT a FROM Address a WHERE a.userDetail.id = :id")
	Iterable<Address> findByUsername(@Param("id") Integer id);

}
