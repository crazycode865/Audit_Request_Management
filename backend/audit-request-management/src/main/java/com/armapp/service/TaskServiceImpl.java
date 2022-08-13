package com.armapp.service;

import com.armapp.model.Talent;
import com.armapp.model.Task;
import com.armapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll()
                .stream()
                .filter(project -> !project.isDeleted())
                .sorted(Comparator.comparing(Task::getTaskId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findAllTasksAssignedTo(String userId) {

        return taskRepository.findByAssignedUserId(userId);
    }

    @Override
    public Task getByTaskId(Integer taskId) {
        return taskRepository.findByTaskId(taskId);
    }

    @Override
    public List<Task> getTasksByReqId(Integer reqId) {
        return taskRepository.findTasksByReqId(reqId);
    }
}
