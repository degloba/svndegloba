package com.degloba.queues;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import static com.google.appengine.api.taskqueue.TaskOptions.Builder.*;


public class PushQueue {
	
	public void addTaskToQueue(String key) throws Exception {
		    
		Queue queue = QueueFactory.getDefaultQueue();    
		queue.add(withUrl("/worker").param("key", key));
	}
	
	
	public void removeTasksFromQueue(String squeue) throws Exception {
		
		Queue queue = QueueFactory.getQueue(squeue);    
		queue.purge();
	}
	

}
