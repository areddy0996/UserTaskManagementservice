package com.assignment.usertask.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.usertask.model.Statistics;
import com.assignment.usertask.model.TaskManagement;
import com.assignment.usertask.model.User;
import com.assignment.usertask.repository.TaskManagementRepository;
import com.assignment.usertask.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class TaskManagementController {

	
	@Autowired
	TaskManagementRepository taskManagementRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/tasks")
	public ResponseEntity<List<TaskManagement>> getAllTasks(
			) {
		try {
			List<TaskManagement> tasks = new ArrayList<TaskManagement>();

			taskManagementRepository.findAll().forEach(tasks::add);
			if (tasks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tasks/completed")
	public ResponseEntity<List<TaskManagement>> getTasksInRange(
			@PathVariable("startdate") Date startdate,@PathVariable("endDate") Date endDate) {
		try {
			
			 List<TaskManagement> tasks =taskManagementRepository.
					 findAllByTaskCreatedDateLessThanEqualAndTaskCreatedDateGreaterThanEqualAndStatus(endDate,startdate,"Completed");

			
			if (tasks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/tasks/overdue")
	public ResponseEntity<List<TaskManagement>> getAllTasksOverdue(
			) {
		try {
			
			long millis=System.currentTimeMillis();  

			
			 
			 List<TaskManagement> tasks = new ArrayList<TaskManagement>();

			taskManagementRepository.findByDueDateLessThanEqual( new java.sql.Date(millis));
			
			if (tasks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tasks/statistics")
	public ResponseEntity<Statistics> getAllTasksStatistics(
			) {
		try {
			
		long totalrec=	taskManagementRepository.count();
		
		long completedrec=taskManagementRepository.findCountByStatus("Completed");
			 
	    long  percentage = (completedrec / totalrec) * 100;

	    Statistics statistics=new Statistics();
		
	    		statistics.setTotalRecords(totalrec);
	    		statistics.setCompletedRecords(completedrec);
	    		statistics.setPercentage(percentage);

			return new ResponseEntity<>(statistics, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/users/{userid}/tasks")
	public ResponseEntity<List<User>> getAllUserTasks(
			@PathVariable("userid") long userid) {
		try {
			List<User> tasks = new ArrayList<User>();

			tasks=userRepository.findbyUserId(userid);
			
			if (tasks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tasks/status/{status}")
	public ResponseEntity<List<TaskManagement>> getAllUserTasksByStatus(
			@PathVariable("status") String status) {
		try {
			List<TaskManagement> tasks = taskManagementRepository.findByStatus(status);
			
			if (tasks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tasks, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<TaskManagement> postTasks
	(@RequestBody TaskManagement taskManagementDTO) {
		try {
			TaskManagement managementDTO = taskManagementRepository
					.save(taskManagementDTO);
			return new ResponseEntity<>(managementDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/tasks/{taskid}/assign")
	public ResponseEntity<User>  assignTasks
	(@PathVariable("taskid") long taskid, @RequestBody User userDTO) {
		try {
	
			Optional<TaskManagement> taskdata = taskManagementRepository.findById(taskid);

		    if (taskdata.isPresent()) {
		    	
		    	userDTO.setTaskId(taskid);
		    	
		        return new ResponseEntity<>(userRepository.save(userDTO), HttpStatus.OK);
			
		    }
		    else
		    {			      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		    	
		    }
		}
		catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
		
	}
	
	@DeleteMapping("/tasks/{taskid}")
	public ResponseEntity<TaskManagement>  deleteTasks
	(@PathVariable("id") long id) {
		try {
			
			 taskManagementRepository.deleteById(id);
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		 catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
	}
	

	
	@PutMapping("/tasks/{taskid}")
	public ResponseEntity<TaskManagement> putTasks
	(@PathVariable("id") long id,@RequestBody TaskManagement taskManagementDTO) {
		try {
			
Optional<TaskManagement> taskdata = taskManagementRepository.findById(id);

			    if (taskdata.isPresent()) {
			    	TaskManagement managementDTO=taskdata.get();
			    	managementDTO.setDescription(taskManagementDTO.getDescription());
			    	managementDTO.setStatus(taskManagementDTO.getStatus());
			    	if(taskManagementDTO.getStatus()!=null && taskManagementDTO.getStatus()
			    			.equalsIgnoreCase("Completed"))
			    	{
			    	    long millis=System.currentTimeMillis();  

				    	managementDTO.setTaskCreatedDate( new java.sql.Date(millis));

			    	}
			    		
			    	managementDTO.setPercent(taskManagementDTO.getPercent());

			    	managementDTO.setTaskDueDate(taskManagementDTO.getTaskDueDate());
			    	managementDTO.setTitle(taskManagementDTO.getTitle());
			    	
			    	
			      return new ResponseEntity<>(taskManagementRepository.save(managementDTO), HttpStatus.OK);
			    } else {
			      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    }
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/tasks/{taskid}/progress")
	public ResponseEntity<TaskManagement> putTaskProgress
	(@PathVariable("id") long id,@RequestBody TaskManagement taskManagementDTO) {
		try {
			
Optional<TaskManagement> taskdata = taskManagementRepository.findById(id);

			    if (taskdata.isPresent()) {
			    	TaskManagement managementDTO=taskdata.get();
			    	managementDTO.setPercent(taskManagementDTO.getPercent());
			    	
			    	
			    	
			      return new ResponseEntity<>(taskManagementRepository.save(managementDTO), HttpStatus.OK);
			    } else {
			      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    }
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
