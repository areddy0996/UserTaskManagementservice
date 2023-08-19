package com.assignment.usertask.model;

import lombok.Data;

public class Statistics {

	private long TotalRecords;
	private long CompletedRecords;
	private long Percentage;
	public long getTotalRecords() {
		return TotalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		TotalRecords = totalRecords;
	}
	public long getCompletedRecords() {
		return CompletedRecords;
	}
	public void setCompletedRecords(long completedRecords) {
		CompletedRecords = completedRecords;
	}
	public long getPercentage() {
		return Percentage;
	}
	public void setPercentage(long percentage) {
		Percentage = percentage;
	}

	
	
	
}
