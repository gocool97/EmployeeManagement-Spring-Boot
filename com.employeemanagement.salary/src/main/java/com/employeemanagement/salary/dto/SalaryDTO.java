package com.employeemanagement.salary.dto;


import com.employeemanagement.user.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeesalary")
public class SalaryDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @OneToOne // One-to-One relationship with UserDTO
	 @JoinColumn(name = "user_id", referencedColumnName = "empId") // Foreign key to UserDTO's empId
	 private UserDTO user; // User reference as a foreign key

	

	private Double grossCTC;
	
	private Double basicSalary;
	
	private Double rentAllowance;
	
	private Double specialAllowance;
	
	private Double convAllowance;


	
	public SalaryDTO(Long id, UserDTO user, Double grossCTC, Double basicSalary, Double rentAllowance,
			Double specialAllowance, Double convAllowance) {
		super();
		this.id = id;
		this.user = user;
		this.grossCTC = grossCTC;
		this.basicSalary = basicSalary;
		this.rentAllowance = rentAllowance;
		this.specialAllowance = specialAllowance;
		this.convAllowance = convAllowance;
	}


	public SalaryDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Double getGrossCTC() {
		return grossCTC;
	}

	public void setGrossCTC(Double grossCTC) {
		this.grossCTC = grossCTC;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getRentAllowance() {
		return rentAllowance;
	}

	public void setRentAllowance(Double rentAllowance) {
		this.rentAllowance = rentAllowance;
	}

	public Double getSpecialAllowance() {
		return specialAllowance;
	}

	public void setSpecialAllowance(Double specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	public Double getConvAllowance() {
		return convAllowance;
	}

	public void setConvAllowance(Double convAllowance) {
		this.convAllowance = convAllowance;
	}

	@Override
	public String toString() {
		return "SalaryDTO [id=" + id + ", user=" + user  + ", grossCTC=" + grossCTC
				+ ", basicSalary=" + basicSalary + ", rentAllowance=" + rentAllowance + ", specialAllowance="
				+ specialAllowance + ", convAllowance=" + convAllowance + "]";
	}

}