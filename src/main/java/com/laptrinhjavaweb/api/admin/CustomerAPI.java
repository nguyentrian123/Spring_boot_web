package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.CustomerAssignmentRequest;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;

@RestController(value="customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ITransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<CustomerDTO> insertCustomer(@RequestBody CustomerDTO customerDTO){
		return ResponseEntity.ok(customerService.save(customerDTO));
	}
	
	@PostMapping("/transaction")
	public TransactionDTO insertTransaction(@RequestBody TransactionDTO transactionDTO,
											@RequestParam(value="customerId") Long customerId){
		transactionDTO.setCustomerId(customerId);
		return transactionService.save(transactionDTO);
	}
	
	@PutMapping
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO){
		return ResponseEntity.ok(customerService.save(customerDTO));
	}

	@GetMapping("/staffs") // dont nest resource  ,TH này ta vừa findbyId vừa FindAll được luôn
	public ResponseDTO loadStaff( @RequestParam(value = "customerid", required = false) long id)
	{
		ResponseDTO result = new ResponseDTO();
		result.setData(userService.findStaffAssginsCustomer(id));
		result.setMessage("success");
		return result;
	}
	
	@PostMapping("/assign")
	public ResponseDTO assginBuilding(@RequestBody CustomerAssignmentRequest customerAssignmentRequest)
	{
		ResponseDTO result = new ResponseDTO();
		customerService.assignmentCustomer(customerAssignmentRequest);
		result.setMessage("success");
		return result;
	}
	
	@DeleteMapping
	public ResponseEntity<Void>  delete(@RequestBody Long[] ids){
		if(ids.length > 0) {
			customerService.deleteCustomer(ids);
		}
		return ResponseEntity.noContent().build();
	}
	
	
	
}
