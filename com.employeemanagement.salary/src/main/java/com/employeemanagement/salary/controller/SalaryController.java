package com.employeemanagement.salary.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.infra.constants.Constants;
import com.employeemanagement.infra.dto.RequestWrapper;
import com.employeemanagement.infra.dto.ResponseWrapper;
import com.employeemanagement.salary.dto.SalaryDTO;
import com.employeemanagement.salary.service.SalaryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/salary")
public class SalaryController {
	
	
	private static final Logger LOGGER = LogManager.getLogger(SalaryController.class);
	
	@Autowired
	SalaryService service;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value = "/addSalary")
	public ResponseEntity<Object> addSalary(@RequestBody RequestWrapper request){
		ResponseWrapper responseWrapper = new ResponseWrapper();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SalaryDTO salary = objectMapper.convertValue(request.getRequestBody(), SalaryDTO.class);
			String response = service.addSalary(salary);
			responseWrapper.setResponseBody(response);
			responseWrapper.setResponseStatus(Constants.OK);
		}  catch (Exception e) {
			LOGGER.info("Exception:"+e.getMessage());
			responseWrapper.setResponseBody(e.getMessage());
			responseWrapper.setResponseStatus(Constants.GENERIC_ERROR);
		}

		return new ResponseEntity(responseWrapper.getResponseBody(), responseWrapper.getHttpHeaders(),
				responseWrapper.getResponseStatus());
	}
	
	 @PutMapping("/updateSalary")
	    public ResponseEntity<String> updateSalary(@RequestParam Long empId, @RequestParam String newRole) {
	        try {
	            service.updateSalaryForRoleChange(empId, newRole);
	            return ResponseEntity.ok("Salary updated successfully for employee ID: " + empId);
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error updating salary: " + e.getMessage());
	        }
	    }
	}

