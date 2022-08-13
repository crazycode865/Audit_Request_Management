package com.armapp.repository;

import com.armapp.model.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Dibya Prakash Ojha
 * @date : 21-Jul-22
 * @project : audit-request-management
 */
public interface AssetsRepository extends JpaRepository<Assets,Integer> {
    @Query("from Assets a where a.task.taskId = :taskId")
    List<Assets> findByTaskId(Integer taskId);

    @Query("from Assets a where a.request.requestId = :requestId")
    List<Assets> findByRequestId(Integer requestId);
}
