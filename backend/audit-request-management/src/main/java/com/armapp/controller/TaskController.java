package com.armapp.controller;

import com.armapp.model.Task;
import com.armapp.repository.OwnerRepository;
import com.armapp.service.ITaskService;
import com.armapp.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private ITaskService iTaskService;

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/tasks")
    @RolesAllowed("report_owner")
    List<Task> getAllTasks() {
        List<Task> tasks = iTaskService.findAll();
        return tasks;
    }

    @GetMapping("/tasks/{userId}")
    ResponseEntity<List<TaskVO>> getAllTasksAssignedTo(@PathVariable String userId) {
        List<Task> tasks = iTaskService.findAllTasksAssignedTo(userId);
        List<TaskVO> taskList = new ArrayList<>();
        for (Task task : tasks) {
            TaskVO taskVO = new TaskVO();
            taskVO.setTaskId(task.getTaskId());
            taskVO.setRequestId(task.getRequest().getRequestId());
            taskVO.setTaskDescription(task.getRequest().getStatus());
            taskVO.setProductionCompanyName(task.getRequest().getProductionName());
            taskVO.setProjectName(task.getRequest().getProjectName());
            taskVO.setTalentName(task.getRequest().getTalentName());
            taskVO.setPriority(task.getRequest().getPriority());
            taskVO.setAuditStartDate(task.getAuditStartDate());
            taskVO.setAuditEndDate(task.getAuditEndDate());
            taskVO.setContractNo(task.getRequest().getContractNo());
            taskVO.setRequestRaised(task.getRequest().getRequestSchedule().getRequestCreated());
            taskVO.setRequestClosed(task.getRequest().getRequestSchedule().getExpectedClosure());
            taskVO.setTaskCreator(task.getRequest().getCreatedBy());
            taskVO.setReportOwner(task.getCategory().getOwner().getOwnerUserId());
            taskVO.setReportOwnerFullName(task.getCategory().getOwner().getOwnerName());
            boolean assetStatus = task.getAssets() == null ? false : true;
            taskVO.setAssetExists(assetStatus);
            taskVO.setTaskCreatorFullName(ownerRepository.getByOwnerUserId(task.getCreatedBy()).getOwnerName());
            taskList.add(taskVO);

        }
        return ResponseEntity.ok().body(taskList);
    }

    /**
     * @return
     * @author BabaSriHarshaErranki Abuthair
     */
    @GetMapping("/tasks-vo")
    ResponseEntity<List<TaskVO>> getAllTasksAssignedTo() {
        List<Task> tasks = iTaskService.findAll();

        //  List<String> contractNumber = iContract.findByContractNum(int talentId,int projectId);
        List<TaskVO> taskList = new ArrayList<>();
        for (Task task : tasks) {
            TaskVO taskVO = new TaskVO();
            taskVO.setTaskId(task.getTaskId());
            taskVO.setRequestId(task.getRequest().getRequestId());
            taskVO.setTaskDescription(task.getRequest().getStatus());
            taskVO.setProductionCompanyName(task.getRequest().getProductionName());
            taskVO.setProjectName(task.getRequest().getProjectName());
            taskVO.setTalentName(task.getRequest().getTalentName());
            taskVO.setPriority(task.getRequest().getPriority());
            taskVO.setAuditStartDate(task.getRequest().getRequestSchedule().getAuditStartDate());
            taskVO.setAuditEndDate(task.getRequest().getRequestSchedule().getAuditEndDate());
            taskVO.setRequestRaised(task.getRequest().getRequestSchedule().getRequestCreated());
            taskVO.setRequestClosed(task.getRequest().getRequestSchedule().getExpectedClosure());
            taskVO.setTaskCreator(task.getRequest().getCreatedBy());
            taskVO.setReportOwner(task.getCategory().getOwner().getOwnerUserId());
            taskVO.setReportOwnerFullName(task.getCategory().getOwner().getOwnerName());
            taskVO.setTaskCreatorFullName(ownerRepository.getByOwnerUserId(task.getCreatedBy()).getOwnerName());
            taskList.add(taskVO);

        }
        return ResponseEntity.ok().body(taskList);
    }

    @GetMapping("/taskVo/taskId/{taskId}")
    ResponseEntity<TaskVO> getTaskById(@PathVariable("taskId") String taskId) {

        Task task = iTaskService.getByTaskId(Integer.valueOf(taskId));
        TaskVO taskVO = new TaskVO();
        taskVO.setTaskId(task.getTaskId());
        taskVO.setRequestId(task.getRequest().getRequestId());
        taskVO.setTaskDescription(task.getRequest().getStatus());
        taskVO.setProductionCompanyName(task.getRequest().getProductionName());
        taskVO.setProjectName(task.getRequest().getProjectName());
        taskVO.setTalentName(task.getRequest().getTalentName());
        taskVO.setContractNo(task.getRequest().getContractNo());
        taskVO.setPriority(task.getRequest().getPriority());
        taskVO.setAuditStartDate(task.getRequest().getRequestSchedule().getAuditStartDate());
        taskVO.setAuditEndDate(task.getRequest().getRequestSchedule().getAuditEndDate());
        taskVO.setRequestRaised(task.getRequest().getRequestSchedule().getRequestCreated());
        taskVO.setRequestClosed(task.getRequest().getRequestSchedule().getExpectedClosure());
        taskVO.setTaskCreator(task.getRequest().getCreatedBy());
        taskVO.setReportOwner(task.getCategory().getOwner().getOwnerUserId());
        taskVO.setReportOwnerFullName(task.getCategory().getOwner().getOwnerName());
        taskVO.setTaskCreatorFullName(ownerRepository.getByOwnerUserId(task.getCreatedBy()).getOwnerName());


        return ResponseEntity.ok().body(taskVO);
    }
    @GetMapping("/tasks/reqId/{reqId}")
    public ResponseEntity<List<Task>> getAllTasksByReqId(@PathVariable("reqId") Integer reqId) {
        return ResponseEntity.ok(iTaskService.getTasksByReqId(reqId));
    }
}