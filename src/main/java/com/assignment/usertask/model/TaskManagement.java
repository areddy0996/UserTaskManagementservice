package com.assignment.usertask.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TaskManagement")
public class TaskManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TaskId")
	private long TaskId;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="TaskCreatedDate")
	private Date TaskCreatedDate;
	@Column(name="dueDate")
	private Date TaskDueDate;
	@Column(name="status")
	private String status;
	@Column(name="percent")
	private Integer percent;
	@Column(name="priority")
    private  Priority priority;

	
	
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public long getTaskId() {
		return TaskId;
	}
	public void setTaskId(long taskId) {
		TaskId = taskId;
	}
	public Integer getPercent() {
		return percent;
	}
	public void setPercent(Integer percent) {
		this.percent = percent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTaskCreatedDate() {
		return TaskCreatedDate;
	}
	public void setTaskCreatedDate(Date taskCreatedDate) {
		TaskCreatedDate = taskCreatedDate;
	}
	public Date getTaskDueDate() {
		return TaskDueDate;
	}
	public void setTaskDueDate(Date taskDueDate) {
		TaskDueDate = taskDueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
