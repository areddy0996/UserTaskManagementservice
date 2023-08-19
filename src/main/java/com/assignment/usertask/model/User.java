package com.assignment.usertask.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="UserData")
public class User {

	@Column(name="UserId")
	private long UserId;
	@Column(name="TaskId")
	private long TaskId;

	public long getUserId() {
		return UserId;
	}
	public void setUserId(long userId) {
		UserId = userId;
	}
	public long getTaskId() {
		return TaskId;
	}
	public void setTaskId(long taskId) {
		TaskId = taskId;
	}
	
	
	
	
	
	
}
