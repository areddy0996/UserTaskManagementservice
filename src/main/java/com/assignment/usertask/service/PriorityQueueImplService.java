package com.assignment.usertask.service;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.assignment.usertask.model.Priority;
import com.assignment.usertask.model.TaskManagement;

public class PriorityQueueImplService {

	
	
	public PriorityQueue<TaskManagement> getTasksOnPriority()
	{
		
		TaskManagement management=new TaskManagement();
		
		long millis=System.currentTimeMillis();  

    
		management.setDescription("test");
		management.setPriority(Priority.HIGH);
		management.setTaskDueDate( new java.sql.Date(millis));
		management.setTaskId(1243);
		management.setTitle("test");
		TaskManagement management1=management;
		management1.setPriority(Priority.LOW);
		management1.setTaskId(35034);
		
		PriorityQueue<TaskManagement> data=new PriorityQueue<TaskManagement>(new CustomComparator());
		data.add(management1);
		data.add(management);
		
		System.out.println(data.poll().getTaskId());
		System.out.println(data.poll().getTaskId());
		
		return null;
		
		
	}
	
}
class CustomComparator implements Comparator<TaskManagement> {

	  @Override
	    public int compare(TaskManagement o,TaskManagement o1) {
	      int val=   o.getPriority().compareTo(o1.getPriority());
	      
	      if(val==0 || val>0)
	      {
	    	  int val1=o.getTaskDueDate().compareTo(o1.getTaskDueDate()); 
	    	  
	    	  return val1;
	      }
	      else
	      {
	    	  return -1;
	      }
	        		
	        		
	        		
	    }

	
}