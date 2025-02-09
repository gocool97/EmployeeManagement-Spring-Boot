package com.employeemanagement.user.dto;

public class RoleUpdateEvent {
	
	private Long empId;
	
	private String newRole;

	public RoleUpdateEvent(Long empId, String newRole) {
		super();
		this.empId = empId;
		this.newRole = newRole;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getNewRole() {
		return newRole;
	}

	public void setNewRole(String newRole) {
		this.newRole = newRole;
	}

	@Override
	public String toString() {
		return "RoleUpdateEvent [empId=" + empId + ", newRole=" + newRole + "]";
	}
	
	

}

