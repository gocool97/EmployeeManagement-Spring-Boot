package com.employeemanagement.user.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.infra.constants.Constants;
import com.employeemanagement.infra.dto.RequestWrapper;
import com.employeemanagement.infra.dto.ResponseWrapper;
import com.employeemanagement.infra.exception.BusinessException;
import com.employeemanagement.user.dto.RoleUpdateEvent;
import com.employeemanagement.user.dto.UserDTO;
import com.employeemanagement.user.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	
	@PostMapping(value = "user/addUser")
	public ResponseEntity<Object> addUser(@RequestBody RequestWrapper request) {
		ResponseWrapper responseWrapper = new ResponseWrapper();
		try {
			LOGGER.info("Incoming Request:" + request.toString());
			ObjectMapper objectMapper = new ObjectMapper();
			UserDTO user = objectMapper.convertValue(request.getRequestBody(), UserDTO.class);
			String response = service.addUser(user);
			responseWrapper.setResponseBody(response);
			responseWrapper.setResponseStatus(Constants.OK);
		} catch (Exception e) {
			LOGGER.error("Exception:", e);
			responseWrapper.setResponseBody(e.getMessage());
			responseWrapper.setResponseStatus(Constants.GENERIC_ERROR);
		}

		return new ResponseEntity<>(responseWrapper.getResponseBody(), responseWrapper.getHttpHeaders(),
				responseWrapper.getResponseStatus());
	}

	@GetMapping(value = "user/getUsers/{empId}")
	public ResponseEntity<Object> getUsers(@PathVariable Long empId) {
		UserDTO user;
		ResponseWrapper responseWrapper = new ResponseWrapper();
		try {
			user = service.getUserById(empId);
			responseWrapper.setResponseBody(user);
			responseWrapper.setResponseStatus(Constants.OK);
		} catch (BusinessException e) {
			LOGGER.info("BussinessException:" + e.getMessage());
			responseWrapper.setResponseBody(e.getMessage());
			responseWrapper.setResponseStatus(Constants.BUSSINESS_ERROR);
		} catch (Exception e) {
			LOGGER.info("Exception:" + e.getMessage());
			responseWrapper.setResponseBody(e.getMessage());
			responseWrapper.setResponseStatus(Constants.GENERIC_ERROR);
		}
		return new ResponseEntity<Object>(responseWrapper.getResponseBody(), responseWrapper.getHttpHeaders(),
				responseWrapper.getResponseStatus());
	}

//	@GetMapping(value = "user/getAll")
//	public ResponseEntity<ResponseWrapper> getAllUsers() throws Exception {
//			ResponseWrapper responseWrapper = service.getRecords();
//			return ResponseEntity.ok(responseWrapper);
//	}

	@GetMapping(value = "user/getAll")
	public ResponseEntity<Object> getAllUsers() {
		List<UserDTO> user;
		ResponseWrapper responseWrapper = new ResponseWrapper();
		try {
			user = service.getRecords();
			responseWrapper.setResponseBody(user);
			responseWrapper.setResponseStatus(Constants.OK);
		} catch (BusinessException e) {
			LOGGER.info("BussinessException:" + e.getMessage());
		} catch (Exception e) {
			LOGGER.info("Exception:" + e.getMessage());
		}
		return new ResponseEntity<>(responseWrapper.getResponseBody(), responseWrapper.getHttpHeaders(),
				responseWrapper.getResponseStatus());
	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@PutMapping(value ="user/update")
//	public ResponseEntity<Object> updateUser(@RequestBody RequestWrapper request) throws BusinessException{
//		ResponseWrapper responseWrapper = new ResponseWrapper();
//		String response = null;
//		try {
//			LOGGER.info("INCOMING"+request.toString());
//			JSONObject requestObject = new JSONObject(request);
//			JSONObject contextObject = new JSONObject(requestObject.get("context").toString());
//			JSONObject requestBodyObject = new JSONObject(requestObject.get("requestBody").toString());
//			String empId = contextObject.getString("empId");
//			String updateType = contextObject.getString("updateType");
//			
//			if(updateType.equals("phoneNumber")) {
//				response = service.updatePhoneNumber(empId,requestBodyObject.getString("newValue"));
//			}
//		}catch(Exception e) {
//			LOGGER.info("Exception:"+e.getMessage());
//			responseWrapper.setResponseBody(e.getMessage());
//			responseWrapper.setResponseStatus(Constants.BUSSINESS_ERROR);
//		}
//		
//		return new ResponseEntity(responseWrapper.getResponseBody(), responseWrapper.getHttpHeaders(),
//				responseWrapper.getResponseStatus());
//
//	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@PutMapping(value = "user/update")
	public ResponseEntity<Object> updateUser(@RequestBody RequestWrapper request) {
	    ResponseWrapper responseWrapper = new ResponseWrapper();
	    String response = null;

	    try {
	        LOGGER.info("INCOMING: " + request.toString());

	        // Use ObjectMapper to parse the request
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> requestMap = objectMapper.convertValue(request, new TypeReference<>() {});

	        // Extract context and requestBody as maps
	        Map<String, Object> contextMap = (Map<String, Object>) requestMap.get("context");
	        Map<String, Object> requestBodyMap = (Map<String, Object>) requestMap.get("requestBody");

	        // Extract fields from the context map
	        String empIdString = (String) requestBodyMap.get("empId"); // Extract empId as String
	        String updateType = (String) requestBodyMap.get("updateType");

	        if ("phoneNumber".equals(updateType)) {
	            // Convert empId to Long
	            Long empId = Long.parseLong(empIdString);

	            // Cast newValue to String
	            String newPhoneNumber = (String) requestBodyMap.get("newValue");

	            // Call the service method
	            response = service.updatePhoneNumber(empId, newPhoneNumber);

	            responseWrapper.setResponseBody(response);
	            responseWrapper.setResponseStatus(Constants.OK);
	        } else if("address".equals(updateType)) {
	        	Long empId = Long.parseLong(empIdString);
	        	
	        	String newAdress = (String) requestBodyMap.get("newValue");
	        	response = service.updateAdress(empId, newAdress);
	        	
	        	responseWrapper.setResponseBody(response);
	            responseWrapper.setResponseStatus(Constants.OK);
	        } else if("modeOfWork".equals(updateType)) {
	        	Long empId = Long.parseLong(empIdString);
	        	
	        	String newModeOfWork = (String) requestBodyMap.get("newValue");
	        	response = service.updateModeOfWork(empId,newModeOfWork);
	        	
	        	responseWrapper.setResponseBody(response);
	            responseWrapper.setResponseStatus(Constants.OK);
	        } else if("role".equals(updateType)) {
	        	Long empId = Long.parseLong(empIdString);
	        	String newRole = (String) requestBodyMap.get("newValue");
	        	response = service.updateRole(empId,newRole);
	        	responseWrapper.setResponseBody(response);
	        	responseWrapper.setResponseStatus(Constants.OK);
	        	
	        	RoleUpdateEvent event = new RoleUpdateEvent(empId,newRole);
	        	eventPublisher.publishEvent(event);
	        	System.out.println("RoleUpdateEvent published: " + event);	        }
	    } catch (BusinessException e) {
	        LOGGER.error("BusinessException: " + e.getMessage());
	        responseWrapper.setResponseBody(e.getMessage());
	        responseWrapper.setResponseStatus(Constants.GENERIC_ERROR);
	    } catch (Exception e) {
	        LOGGER.error("Exception: " + e.getMessage());
	        responseWrapper.setResponseBody("Internal Server Error: " + e.getMessage());
	        responseWrapper.setResponseStatus(Constants.BUSSINESS_ERROR);
	    }

	    // Log the response status to debug invalid values
	    LOGGER.info("Response Status: " + responseWrapper.getResponseStatus());

	    return new ResponseEntity<>(responseWrapper.getResponseBody(), responseWrapper.getHttpHeaders(),
	            responseWrapper.getResponseStatus());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(value = "user/deleteUser/{empId}")
	public ResponseEntity<Object> deleteEmp(@PathVariable Long empId){
		ResponseWrapper responseWrapper = new ResponseWrapper();
		try {
			String response = service.deleteEmp(empId);
			responseWrapper.setResponseBody(response);
			responseWrapper.setResponseStatus(Constants.OK);
		}catch(BusinessException e) {
		LOGGER.info("BussinessException:"+e.getMessage());
		responseWrapper.setResponseBody(e.getMessage());
		responseWrapper.setResponseStatus(Constants.BUSSINESS_ERROR);
	}catch (Exception e) {
		LOGGER.info("Exception:"+e.getMessage());
		responseWrapper.setResponseBody(e.getMessage());
		responseWrapper.setResponseStatus(Constants.GENERIC_ERROR);
	}
	return new ResponseEntity(responseWrapper.getResponseBody(), responseWrapper.getHttpHeaders(),
			responseWrapper.getResponseStatus());
	}
}

