package com.employeemanagement.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagement.user.dto.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, String> {
	UserDTO findByEmpId(Long empId);
	@SuppressWarnings("null")
	List<UserDTO> findAll();

}
