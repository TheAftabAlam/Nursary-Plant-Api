package com.nursery.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.CustomerException;
import com.nursery.exceptions.OrderException;
import com.nursery.model.Orders;
import com.nursery.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	// ADD ORDER
	@PostMapping("/addOrder/{uuid}")
	public ResponseEntity<Orders> addOrder(@PathVariable("uuid") String uuid, @Valid @RequestBody Orders order) throws CustomerException {
		Orders ord = orderService.addOrder(order, uuid);
		
		return new ResponseEntity<Orders>(ord,HttpStatus.CREATED);
	}

	// UPDATE ORDER
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order) throws OrderException {
		Orders ord = orderService.updateOrder(order);
		
		return new ResponseEntity<Orders>(ord,HttpStatus.ACCEPTED);
	}

	// DELETE ORDER
	@DeleteMapping("/deleteOrder/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteOrder(@RequestBody Orders order) throws OrderException {
		Orders ord = orderService.deleteOrder(order);
		if (ord == null) {
			return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Problem in deleting", HttpStatus.BAD_REQUEST);
		}
	}

	// VIEW ALL ORDERS
	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> viewAllOrders() throws OrderException {
		List<Orders> orders= orderService.viewAllOrders();
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}

	// VIEW ORDER BY ID
	@GetMapping("/findOrder/{orderid}")
	public ResponseEntity<Orders> viewOrder(@PathVariable("orderid") int orderId) throws OrderException {
		Orders orders= orderService.viewOrder(orderId);
		
		return new ResponseEntity<Orders>(orders,HttpStatus.OK);
	}
}
