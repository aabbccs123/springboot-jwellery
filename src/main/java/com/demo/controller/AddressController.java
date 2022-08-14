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
import com.demo.model.UserDetail;
import com.demo.repository.AddressRepository;
import com.demo.repository.UserDetailRepository;

@RestController
@CrossOrigin(origins = "*")
public class AddressController {
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	@GetMapping("/address")
	public  Iterable<Address> getAllAddresses(){
		return addressRepository.findAll();
	}
	
	@PostMapping("/address")
	public Address createAddress(@RequestBody Address address, @RequestParam("username") String username) {
		     UserDetail detail = userDetailRepository.findByUsername(username);
		     address.setUserDetail(detail);
			 return  addressRepository.save(address);
	    }
	
	@GetMapping("/address/{id}")
	public Address getOrderbyId(@PathVariable(value = "id") Integer id) {
		return addressRepository.findById(id).get();
	}
	
	@PutMapping("/address/{id}")
    public ResponseEntity<Address> updateOrder(@PathVariable(value = "id") Integer id,
                                                 @RequestBody Address addressDetails) {
		System.out.println("welcome");
        Address address = addressRepository.findById(id).get();
        
        address.setAddress(addressDetails.getAddress());
        address.setName(addressDetails.getName());
        address.setMobileNumber(addressDetails.getMobileNumber());
        //address.setUserDetail(addressDetails.getUserDetail());
       
        final Address updatedAddress = addressRepository.save(address);
        return ResponseEntity.ok(updatedAddress);
    }
	
	@DeleteMapping("/address/{id}")
	public String deleteOrder(@PathVariable(value = "id") Integer id) {
		
	     Address order = addressRepository.findById(id).get();
	        addressRepository.delete(order);
	        return "Product with id "+id+" deleted successfully";
	        
	}
	
	@GetMapping("/addressByUsername")
	public  Iterable<Address> getAddressesByUsername(@RequestParam("username") String username){
		int id = userDetailRepository.findByUsernameId(username);
		return addressRepository.findByUsername(id);
	}

}
