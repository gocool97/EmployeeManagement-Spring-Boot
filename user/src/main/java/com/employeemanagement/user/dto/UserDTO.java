package com.employeemanagement.user.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class UserDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;

	@NotBlank(message = "Name cannot be blank")
	private String name;

	@NotBlank(message = "phoneNumber cannot be blank")
	private String phoneNumber;

	@NotBlank(message = "Address cannot be blank")
	private String address;

	@NotBlank(message = "Role cannot be blank")
	private String role;

	@NotNull(message = "joiningDate cannot be blank")
	private String joiningDate;

	@NotBlank(message = "modeOfWork cannot be blank")
	private String modeOfWork;

	
	public UserDTO() {
		
	}
	public UserDTO(Long empId, @NotBlank(message = "Name cannot be blank") String name,
			@NotBlank(message = "phoneNumber cannot be blank") String phoneNumber,
			@NotBlank(message = "Address cannot be blank") String address,
			@NotBlank(message = "Role cannot be blank") String role,
			@NotNull(message = "joiningDate cannot be blank") String joiningDate,
			@NotBlank(message = "modeOfWork cannot be blank") String modeOfWork) {
		super();
		this.empId = empId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
		this.joiningDate = joiningDate;
		this.modeOfWork = modeOfWork;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getModeOfWork() {
		return modeOfWork;
	}

	public void setModeOfWork(String modeOfWork) {
		this.modeOfWork = modeOfWork;
	}

	@Override
	public String toString() {
		return "UserDTO [empId=" + empId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", role=" + role + ", joiningDate=" + joiningDate + ", modeOfWork=" + modeOfWork + "]";
	}

}
