package com.armapp.repository;

import com.armapp.model.Message;
import com.armapp.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query(value="SELECT m FROM Message m WHERE m.task.taskId = :taskId")
    List<Message> findAllByTaskId(Integer taskId);

}
