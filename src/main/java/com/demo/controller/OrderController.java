package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Address;
import com.demo.model.Image;
import com.demo.model.Order;
import com.demo.model.UserDetail;
import com.demo.repository.ImageRepository;
import com.demo.repository.OrderRepository;
import com.demo.repository.UserDetailRepository;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	@GetMapping("/orders")
	public  Iterable<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	@PostMapping("/orders")
	public Order createOrder(@RequestBody Order orders) {
			 return  orderRepository.save(orders);
	    }
	
	@GetMapping("/orders/{id}")
	public Order getOrderbyId(@PathVariable(value = "id") Integer id) {
		return orderRepository.findById(id).get();
	}
	
	@PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Integer id,
                                                 @RequestBody Order orderDetails) {
		
        Order order = orderRepository.findById(id).get();
        
        order.setStatus(orderDetails.getStatus());
        order.setProductIds(orderDetails.getProductIds());
        order.setUserDetail(orderDetails.getUserDetail());
        order.setAddress(orderDetails.getAddress());
        order.setCost(orderDetails.getCost());
       
        final Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }
	
	@DeleteMapping("/orders/{id}")
	public String deleteOrder(@PathVariable(value = "id") Integer id) {
		
	     Order order = orderRepository.findById(id).get();
	        orderRepository.delete(order);
	        return "Product with id "+id+" deleted successfully";
	    }
	
	@GetMapping("/ordersByUsername")
	public Order createOrderByUsername(@RequestParam("username") String  username,
			                           @RequestParam("address") String  address,
			                           @RequestParam("cost") long  cost) {
		UserDetail detail = userDetailRepository.findByUsername(username);
		Order orders = new Order();
		orders.setProductIds(detail.getCartIds());
		orders.setStatus("Not Delivered");
		orders.setUserDetail(detail);
		orders.setAddress(address);
		orders.setCost(cost);
		return  orderRepository.save(orders);
	    }
	
	@GetMapping("/ordeByUserDetailId")
	public  Iterable<Order> getAddressesByUsername(@RequestParam("username") String username){
		int id = userDetailRepository.findByUsernameId(username);
		return orderRepository.findByUserDetailId(id);
	}

}
