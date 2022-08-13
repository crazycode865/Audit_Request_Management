package com.armapp.service;

import com.armapp.exception.InvalidIdException;
import com.armapp.model.Request;

import java.util.List;

/**
 * @author - Akash Kanaparthi
 * @date - 08-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
public interface IRequestService {
    Request save(Request request);
    List<Request> getAll();
    Request getById(Integer id);
    List<Request> findByAssignedUserId(String createdByUser);

}
