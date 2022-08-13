/**
 * @Author:Awadhesh,Madhu
 * @Date:11-07-2022
 * @Time:18:39
 * @Project Name:Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
package com.armapp.controller;

import com.armapp.model.Talent;
import com.armapp.model.TalentProject;
import com.armapp.service.ITalentService;
import com.armapp.vo.TalentVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class TalentController {

    private ITalentService iTalentService;

    @Autowired
    public void setiTalentService(ITalentService iTalentService) {
        this.iTalentService = iTalentService;
    }


    @GetMapping("/talents/{keyword}")
    @RolesAllowed({"manager","report_owner"})
    public ResponseEntity<List<TalentVO>> search(@PathVariable("keyword") String keyword) throws NullPointerException {
        List<Talent> talents = iTalentService.getByTalentNameLike(keyword);


        List<TalentVO> talentsVOList = new ArrayList<TalentVO>();
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> myMappingFiles = new ArrayList<String>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);
        for (Talent talent : talents) {
            TalentVO talentsVO = mapper.map(talent, TalentVO.class);
            talentsVOList.add(talentsVO);
        }
        return ResponseEntity.ok().headers(httpHeaders -> httpHeaders
                            .add("desc", "Getting Talents by Name"))
                    .body(talentsVOList);
    }


    @GetMapping("/talents")
    @RolesAllowed({"manager","report_owner"})
    public ResponseEntity<List<TalentVO>> search() throws NullPointerException {
        List<Talent> talents = iTalentService.getAll();

        List<TalentVO> talentsVOList = new ArrayList<TalentVO>();
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> myMappingFiles = new ArrayList<String>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);
        for (Talent talent : talents) {
            TalentVO talentsVO = mapper.map(talent, TalentVO.class);
            talentsVOList.add(talentsVO);
        }
        return ResponseEntity.ok().headers(httpHeaders -> httpHeaders
                        .add("desc", "Getting All Talents"))
                .body(talentsVOList);
    }

}
