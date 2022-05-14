package com.capgemini.main.exception;

public class ProblemFormat {

	private String title;
	private int stausCode;
	private String message;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStausCode() {
		return stausCode;
	}
	public void setStausCode(int stausCode) {
		this.stausCode = stausCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
