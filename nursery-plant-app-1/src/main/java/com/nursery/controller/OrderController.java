package com.nursery.controller;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.dto.OrderDto;
import com.nursery.dto.OrderResponseDto;
import com.nursery.exceptions.OrderException;
import com.nursery.model.Customer;
import com.nursery.model.Orders;
import com.nursery.service.CustomerService;
import com.nursery.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;
	
	
	// ADD ORDER
//	@PostMapping("/addOrder/{uuid}")
//	public ResponseEntity<Orders> addOrder(@PathVariable("uuid") String uuid, @Valid @RequestBody Orders order) throws CustomerException {
//		Orders ord = orderService.addOrder(order, uuid);
//		
//		return new ResponseEntity<Orders>(ord,HttpStatus.CREATED);
//	}

	// UPDATE ORDER
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<Orders> updateOrder(@Valid @RequestBody Orders order,@RequestParam(required = false) String key) throws OrderException {
		Orders ord = orderService.updateOrder(order,key);
		
		return new ResponseEntity<Orders>(ord,HttpStatus.ACCEPTED);
	}

	// DELETE ORDER
	@DeleteMapping("/deleteOrder/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteOrder(@Valid @RequestBody Orders order,@RequestParam(required = false) String key) throws OrderException {
		Orders ord = orderService.deleteOrder(order,key);
		if (ord == null) {
			return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Problem in deleting", HttpStatus.BAD_REQUEST);
		}
	}

	// VIEW ALL ORDERS
	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> viewAllOrders(@RequestParam(required = false) String key) throws OrderException {
		
		
		List<Orders> orders= orderService.viewAllOrders(key);
		
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
		
	}

	// VIEW ORDER BY ID
	@GetMapping("/findOrder/{orderid}")
	public ResponseEntity<Orders> viewOrder(@PathVariable("orderid") int orderId,
			@RequestParam(required = false) String key) throws OrderException {
		Orders orders= orderService.viewOrder(orderId,key);
		
		return new ResponseEntity<Orders>(orders,HttpStatus.OK);
	}

	    @PostMapping("/placeOrder")
	    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderDto orderDTO,
	    		@RequestParam(required = false) String key) throws Exception {
	      
	        OrderResponseDto responseOrderDTO = new OrderResponseDto();
	        float amount = orderService.getCartAmount(orderDTO.getCartItems(),key);

	        Customer customer = new Customer();
	        customer.setCustomerName(orderDTO.getCustomerName());
	        customer.setCustomerEmail(orderDTO.getCustomerEmail());
	       
	        Integer customerIdFromDb = customerService.isCustomerPresent(customer);
	        if (customerIdFromDb != null) {
	            customer.setCustomerID(customerIdFromDb);
	           
	        }else{
	            customer = customerService.addCustomer(customer);
	          
	        }
	        Orders order = new Orders(orderDTO.getOrderDescription(), customer, orderDTO.getCartItems());
	        order = orderService.addOrder(order, key);
	        

	        responseOrderDTO.setAmount(amount);
	        responseOrderDTO.setDate(LocalDateTime.now());
	        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
	        responseOrderDTO.setOrderId(order.getBookingOrderId());
	        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

	       

	        return ResponseEntity.ok(responseOrderDTO);
	    }
	
	
	
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
