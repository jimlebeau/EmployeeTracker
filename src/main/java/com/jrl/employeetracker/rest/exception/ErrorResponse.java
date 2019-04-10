package com.jrl.employeetracker.rest.exception;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse {
	
	private String errorMessage;
	private List<String> details = new ArrayList<>();
	
	public ErrorResponse() {
		
	}
	
	public ErrorResponse(String errorMessage, List<String> details) {
		this.errorMessage = errorMessage;
		this.details = details;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
