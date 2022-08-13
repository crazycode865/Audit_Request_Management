package com.armapp.controller;



import com.armapp.model.Production;
import com.armapp.service.IProductionService;
import com.armapp.vo.ProductionVO;
import org.dozer.DozerBeanMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProductionController {

    private IProductionService iProductionService;

    public ProductionController(IProductionService iProductionService) {
        this.iProductionService = iProductionService;
    }

    @GetMapping("/productions/{companyName}")
    @RolesAllowed({"manager","report_owner"})
    ResponseEntity<List<ProductionVO>> getByProductionCompanyNameLike(@PathVariable("companyName") String companyName) {
        List<Production> names = iProductionService.getByProductionCompanyNameLike(companyName);
        List<ProductionVO> productionVOList = new ArrayList<>();
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> myMappingFiles = new ArrayList<>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);
        for (Production production : names) {
            ProductionVO productionVO = mapper.map(production, ProductionVO.class);
            productionVOList.add(productionVO);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "get production company names like"))
                .body(productionVOList);
    }


    @GetMapping("/productions")
    @RolesAllowed({"manager","report_owner"})
    ResponseEntity<List<ProductionVO>> getAllProductionCompany() {

        List<Production> names = iProductionService.getAll();
        List<ProductionVO> productionVOList = new ArrayList<>();
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<String> myMappingFiles = new ArrayList<>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);
        for (Production production : names) {
            ProductionVO productionVO = mapper.map(production, ProductionVO.class);
            productionVOList.add(productionVO);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "get all production company"))
                .body(productionVOList);
    }
}
