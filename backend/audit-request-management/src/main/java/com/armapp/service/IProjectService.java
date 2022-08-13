package com.armapp.service;

import com.armapp.exception.InvalidIdException;
import com.armapp.model.Project;

import java.util.List;
import java.util.Set;

/**
 * @author - Akash Kanaparthi
 * @date - 08-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
public interface IProjectService {

    void addProject(Set<Project> project);
    void updateProject(Project project);
    void deleteProject(int projectId) throws InvalidIdException;
    Project getById(int projectId) throws InvalidIdException;

    List<Project> getAll();

    List<Project> getAllProjectNames(Integer productionId);
    List<Project> getTypedProjectNames(Integer productionId,String typedProjectName);
}
