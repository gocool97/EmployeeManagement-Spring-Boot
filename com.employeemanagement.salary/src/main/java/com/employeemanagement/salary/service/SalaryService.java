package com.employeemanagement.salary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.infra.exception.BusinessException;
import com.employeemanagement.salary.dto.SalaryDTO;
import com.employeemanagement.salary.repository.SalaryRepository;

@Service
public class SalaryService {

	@Autowired
	private SalaryRepository salRepo;

	public String addSalary(SalaryDTO newSalary) throws Exception {
		try {
			if (newSalary.getGrossCTC() < newSalary.getBasicSalary() + newSalary.getRentAllowance()
					+ newSalary.getSpecialAllowance() + newSalary.getConvAllowance()) {
				throw new BusinessException("Salary cannot be less than Gross CTC");
			} else if (newSalary.getGrossCTC() > newSalary.getBasicSalary() + newSalary.getRentAllowance()
					+ newSalary.getSpecialAllowance() + newSalary.getConvAllowance()) {
				throw new BusinessException("Gross CTC cannot be greater than total CTC");
			}
			salRepo.save(newSalary);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "Salary of User is Added Successfully";
	}

	public void updateSalaryForRoleChange(Long empId, String newRole) {
		System.out.println("Updating salary for empId: " + empId + ", newRole: " + newRole);

		SalaryDTO salary = salRepo.findByUser_EmpId(empId);

		if (salary != null) {
			Double currentGrossCTC = salary.getGrossCTC();
			Double incrementPercentage = getIncrementPercentage(newRole);
			Double newGrossCTC = currentGrossCTC + (currentGrossCTC * incrementPercentage);

			System.out.println("Current Gross CTC: " + currentGrossCTC);
			System.out.println("Increment Percentage: " + incrementPercentage);
			System.out.println("New Gross CTC: " + newGrossCTC);

			salary.setGrossCTC(newGrossCTC);
			salRepo.save(salary);
		}
	}

	private Double getIncrementPercentage(String role) {
		if (role == null || role.isEmpty()) {
			return 0.0;
		}
		switch (role.trim().toLowerCase()) { // Normalize role
		case "developer":
			return 0.05;
		case "senior developer":
			return 0.10;
		case "principle developer":
			return 0.20;
		default:
			return 0.0;
		}
	}
}
