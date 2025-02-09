package com.employeemanagement.salary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.salary.dto.SalaryDTO;

public interface SalaryRepository extends JpaRepository<SalaryDTO, Long> {

	SalaryDTO findByUser_EmpId(Long empId);

}

