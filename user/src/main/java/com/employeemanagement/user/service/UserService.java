package com.employeemanagement.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.employeemanagement.infra.exception.BusinessException;
import com.employeemanagement.user.client.SalaryClient;
import com.employeemanagement.user.dto.UserDTO;
import com.employeemanagement.user.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RestTemplate restTemplate;
	

	@Autowired(required = false)
    private SalaryClient salaryClient;

	// Adding New User
	public String addUser(UserDTO newUser) throws Exception {
		try {
			userRepository.save(newUser);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "User is Added Successfully";

	}

	public UserDTO getUserById(Long empId) throws Exception {
		UserDTO user;
		try {
			user = userRepository.findByEmpId(empId);
			if (user == null) {
				throw new BusinessException("User not found");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return user;
	}

	public List<UserDTO> getRecords() throws Exception {
	    List<UserDTO> users;
	    try {
	        users = userRepository.findAll();
	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	    return users;
	}

	public String updatePhoneNumber(Long empId, String newPhoneNumber) throws Exception {
		UserDTO user;
		try {
			user = getUserById(empId);
			if(user == null) {
				throw new BusinessException("User Does Not Exist");
			}
			else if(user.getPhoneNumber().equals(newPhoneNumber)) {
				throw new BusinessException("Old and New Phone Cannot be Same");
			}
			user.setPhoneNumber(newPhoneNumber);
			userRepository.save(user);
		}catch (Exception e) {
			System.out.println("[Exception" + e.fillInStackTrace() + "]");
			throw new Exception(e.getMessage());
		}
		return "Phone Number Updated";
	}

	public String updateAdress(Long empId, String newAdress) throws Exception {
		
		UserDTO user;
		try {
			user = getUserById(empId);
			if(user == null) {
				throw new BusinessException("User Dows Not Exist");
			}
			else if(user.getAddress().equals(newAdress)) {
				throw new BusinessException("Old and New Address Cannot be Same");
			}
			user.setAddress(newAdress);
			userRepository.save(user);
		}catch(Exception e) {
			System.out.println("[Exception" + e.fillInStackTrace() + "]");
			throw new Exception(e.getMessage());
		}
		return "Address has been Updated Successfully";
	}

	public String updateModeOfWork(Long empId, String newModeOfWork) throws Exception {
		UserDTO user;
		try {
			user = getUserById(empId);
			if(user == null) {
				throw new BusinessException("User Does Not Exist");
			}
			else if(user.getModeOfWork().equals(newModeOfWork)) {
				throw new BusinessException("Old and New Mode work cannot be same");
			}
			user.setModeOfWork(newModeOfWork);
			userRepository.save(user);
		}catch(Exception e) {
			System.out.println("[Exception" + e.fillInStackTrace() + "]");
			throw new Exception(e.getMessage());
		}
		return "New Mode Of Work has been Updated Successfully";
	}
	
	public String updateRole(Long empId, String newRole) throws Exception {
		UserDTO user;
		try {
			user = getUserById(empId);
			if(user == null) {
				throw new BusinessException("User Does Not Exist");
			} else if(user.getRole().equals(newRole)) {
				throw new BusinessException("Old and New Role Cannot be Same");
			}
			user.setRole(newRole);
			userRepository.save(user);
			
	        String responseToUpdate = salaryClient.updateSalary(empId, newRole);

			
			 String salaryServiceUrl = "http://localhost:8080/salary/updateSalary"; // Replace with actual Salary Service URL
		        String response = restTemplate.exchange(
		                salaryServiceUrl + "?empId=" + empId + "&newRole=" + newRole,
		                HttpMethod.PUT,
		                null,
		                String.class
		        ).getBody();
		}catch(Exception e) {
			System.out.println("[Exception" + e.fillInStackTrace() + "]");
			throw new Exception(e.getMessage());
		}
		return "New Role is added Successfully";
	}

	public String deleteEmp(Long empId) throws Exception {
		UserDTO user;
		try {
			user = userRepository.findByEmpId(empId);
			if(user == null) {
				throw new BusinessException("User Does not Exist");
			}
			userRepository.delete(user);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return "User has been deleted successfully";
	}



}
