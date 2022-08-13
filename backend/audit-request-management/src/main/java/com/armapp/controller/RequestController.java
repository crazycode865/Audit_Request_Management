package com.armapp.controller;

import com.armapp.model.*;
import com.armapp.repository.CategoryRepository;
import com.armapp.service.IRequestService;
import com.armapp.vo.CreateRequestVO;
import com.armapp.vo.RequestVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class RequestController {

    private IRequestService iRequestService;
    private CategoryRepository categoryRepo;

    @Autowired
    public void setiRequestService(IRequestService iRequestService) {
        this.iRequestService = iRequestService;
    }

    @Autowired
    public void setCategoryRepo(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @GetMapping("/request/{userId}")
    ResponseEntity<List<RequestVO>> getAllRequests(@PathVariable String userId) {
        List<Request> requests = iRequestService.findByAssignedUserId(userId);
        List<RequestVO> requestVOList = new ArrayList<>();
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> myMappingFiles = new ArrayList<>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);
        for (Request request : requests) {
            boolean assetStatus = request.getAssets() == null? false: true;
            RequestVO requestVO = mapper.map(request, RequestVO.class);
            requestVO.setAssetExists(assetStatus);
            requestVOList.add(requestVO);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "get request vo list"))
                .body(requestVOList);
    }
    @GetMapping("/request/id/{id}")
    ResponseEntity<Request> getRequestById(@PathVariable("id") Integer id) {
        Request request = iRequestService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "get request by id"))
                .body(request);
    }


    /**
     * @return
     * @author BabaSriHarshaErranki
     */
    @GetMapping("/requests-vo")
//    @RolesAllowed("manager")
    ResponseEntity<List<RequestVO>> getAllRequestVOs() {
        List<Request> requests = iRequestService.getAll();
        List<RequestVO> requestVOList = new ArrayList<RequestVO>();
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> myMappingFiles = new ArrayList<>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);
        for (Request request : requests) {
            boolean assetStatus = request.getAssets() == null? false: true;
            RequestVO requestVO = mapper.map(request, RequestVO.class);
            requestVO.setAssetExists(assetStatus);
            requestVOList.add(requestVO);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "get request vo list"))
                .body(requestVOList);
    }

    @PostMapping("/requests")
//    @RolesAllowed("manager")
    ResponseEntity<String> createRequest(@RequestBody CreateRequestVO createRequestVO) {
        Request request = new Request();
        request.setRequestId(createRequestVO.getRequestId());
        request.setPriority(createRequestVO.getPriority());
        request.setUnionName(createRequestVO.getUnionName());
        request.setStatus(createRequestVO.getStatus());
        request.setProductionName(createRequestVO.getProductionName());
        request.setTalentName(createRequestVO.getTalentName());
        request.setContractNo(createRequestVO.getContractNo());
        request.setProjectName(createRequestVO.getProjectName());
        request.setRequestCreatedDate(createRequestVO.getRequestSchedule().getRequestCreated());
        request.setContractDate(createRequestVO.getContractDate());
        request.setCreatedBy(createRequestVO.getCreatedBy());
        request.setCreatedAt(LocalDateTime.now());

        RequestSchedule requestSchedule = createRequestVO.getRequestSchedule();
//        RequestSchedule requestSchedule = new RequestSchedule();
        requestSchedule.setCreatedBy(createRequestVO.getCreatedBy());
        requestSchedule.setCreatedAt(LocalDateTime.now());
        requestSchedule.setRequest(request);

        Set<Task> taskSet = new HashSet<>();
        Category category;
        Category categoryById;
        for (Task task1 : createRequestVO.getTasksList()) {
            Task task = new Task();
            category = task1.getCategory();
            categoryById = categoryRepo.findById(category.getCategoryId()).get();
            task.setRequest(request);
            task.setCategory(categoryById);
            task.setAuditStartDate(task1.getAuditStartDate());
            task.setAuditEndDate(task1.getAuditEndDate());
            task.setCreatedAt(LocalDateTime.now());
            task.setCreatedBy(createRequestVO.getCreatedBy());
            taskSet.add(task);
        }
        request.setTasksList(taskSet);
        request.setRequestSchedule(requestSchedule);
        iRequestService.save(request);
        return ResponseEntity.status(HttpStatus.OK).body("Created Request");
    }
}