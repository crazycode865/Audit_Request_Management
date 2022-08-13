package com.armapp.repository;

import com.armapp.model.Request;
import com.armapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value="SELECT r FROM Request r WHERE r.createdBy = :createBy")
    List<Request> findByAssignedUserId(String createBy);

}
