package com.employeemanagement.infra.exception;

public class BusinessException extends Exception{
	public BusinessException(String errorMessage) {
		super(errorMessage);
	}
}