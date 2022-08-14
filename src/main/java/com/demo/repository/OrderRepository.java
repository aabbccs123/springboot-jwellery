package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.model.Address;
import com.demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("SELECT a FROM Order a WHERE a.userDetail.id = :id")
	Iterable<Order> findByUserDetailId(@Param("id") Integer id);

}
