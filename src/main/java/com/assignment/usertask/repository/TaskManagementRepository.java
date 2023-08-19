package com.assignment.usertask.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignment.usertask.model.TaskManagement;


@Repository
public interface TaskManagementRepository extends
JpaRepository<TaskManagement, Long> {

	public List<TaskManagement> findByDueDateLessThanEqual(Date currentdate);
	public List<TaskManagement> findByStatus(String Status);

	public List<TaskManagement> findAllByTaskCreatedDateLessThanEqualAndTaskCreatedDateGreaterThanEqualAndStatus(Date endDate, Date startDate,String status);
	
	@Query("SELECT COUNT(u) FROM TaskManagement u WHERE u.status=?1")
    long findCountByStatus(String status);
}
