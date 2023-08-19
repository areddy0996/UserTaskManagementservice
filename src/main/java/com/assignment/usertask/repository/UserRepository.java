package com.assignment.usertask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.usertask.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	List<User> findbyUserId(long userid);
}
