package com.employeemanagement.salary.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.employeemanagement.salary.service.SalaryService;
import com.employeemanagement.user.dto.RoleUpdateEvent;

@Component
public class RoleUpdateListener {

	@Autowired
	private SalaryService service;
	
	@EventListener
	public void handleRoleUpdateEvent(RoleUpdateEvent event) {
		System.out.println("RoleUpdateEvent received for empId: " + event.getEmpId() + ", newRole: " + event.getNewRole());
		service.updateSalaryForRoleChange(event.getEmpId(),event.getNewRole());
	}
}
