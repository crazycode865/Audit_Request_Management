package com.armapp.controller;

import com.armapp.model.ReqID;
import com.armapp.model.Talent;
import com.armapp.service.IReqIDService;
import com.armapp.vo.ReqIDVO;
import com.armapp.vo.TalentVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ReqIDController {

    private IReqIDService IReqIDService;

    @Autowired
    public void setIReqIDService(com.armapp.service.IReqIDService IReqIDService) {
        this.IReqIDService = IReqIDService;
    }

    @GetMapping("/reqid")
    @RolesAllowed("manager")
    ResponseEntity<ReqIDVO> getNextReqID() {
        ReqID reqID = new ReqID();
        reqID.setCreatedAt(LocalDateTime.now());
        ReqID newReqId = IReqIDService.createReqID(reqID);
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> myMappingFiles = new ArrayList<String>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);
           ReqIDVO reqIdVO = mapper.map(newReqId, ReqIDVO.class);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "new req id"))
                .body(reqIdVO);
    }

}
