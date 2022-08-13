package com.armapp.service;

import com.armapp.model.Task;

import java.util.List;

/**
 * @author DibyaPrakashOjha
 */

public interface ITaskService {
    public List<Task> findAll();

    public List<Task> findAllTasksAssignedTo(String userId);
    Task getByTaskId(Integer taskId);

    List<Task> getTasksByReqId(Integer reqId);

}
