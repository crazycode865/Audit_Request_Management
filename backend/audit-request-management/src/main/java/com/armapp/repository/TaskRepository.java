package com.armapp.repository;

import com.armapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value="SELECT t FROM Task t WHERE t.category.owner.ownerUserId = :ownerUserId")
    List<Task> findByAssignedUserId(String ownerUserId);

    @Query(value="SELECT t FROM Task t WHERE t.taskId = :taskId")
    Task findByTaskId(Integer taskId);

    @Query(value="SELECT t FROM Task t WHERE t.request.requestId = :reqId")
    List<Task> findTasksByReqId(Integer reqId);
}
