package com.armapp.controller;

import com.armapp.model.Category;
import com.armapp.service.ICategoryService;
import com.armapp.vo.CategoryVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - Akash Kanaparthi
 * @date - 15-07-2022
 * @project - audit-request-management
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CategoryController {

    private ICategoryService categoryService;

    @Autowired
    public void setCategoryService(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     *  saves the category to the database
     * @author - AkashKanaparthi
     * @param category
     * @return string message
     */
    @PostMapping("/create-category")
    @RolesAllowed("manager")
    ResponseEntity<String> saveCategory(@RequestBody Category category){
//        Owner owner = category.getOwner();
//        Owner owner1 = new Owner();
//        owner1.setOwnerId(owner.getOwnerId());
//        owner1.setCreatedAt(LocalDateTime.now());
//        owner1.setOwnerName(owner.getOwnerName());
//        owner1.setCreatedBy(owner.getCreatedBy());
        category.setCreatedBy(System.getProperty("user.name"));
        category.setCreatedAt(LocalDateTime.now());
//        category.setOwner(owner1);
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category is saved successfully");
    }

    /**
     * api to update the category
     * @author - AkashKanaparthi
     * @param category
     * @return String message
     */
    @PutMapping("/update-category")
    @RolesAllowed("manager")
    ResponseEntity<String> updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return ResponseEntity.ok("Updated Successfully");
    }

    /**
     * this should not be implemented but only for future purpose we have created it
     * @author - AkashKanaparthi
     * @param categoryId
     * @return string message
     */
    @PutMapping("/delete-category/{id}")
    @RolesAllowed("manager")
    ResponseEntity<String> deleteCategory(@PathVariable("id") int categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Deleted Successfully");
    }

    /**
     *
     * @author - AkashKanaparthi
     * @param categoryId
     * @return single category by id
     */
    @GetMapping("/category")
    @RolesAllowed("manager")
    ResponseEntity<Category> getCategory(@RequestParam int categoryId){
        return ResponseEntity.ok(categoryService.getById(categoryId));
    }

    /**
     * shows all the categories present in the database
     * @return List of categories
     */
    @GetMapping("/categories")
    @RolesAllowed("manager")
    ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/categories_vo")
    ResponseEntity<List<CategoryVO>> getAllTasksAssignedTo() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        List<Category> categories = categoryService.getAll();
        List<CategoryVO>categoryVOList = new ArrayList<>();
        List<String> myMappingFiles = new ArrayList<>();
        myMappingFiles.add("dozerBeanMapping.xml");
        mapper.setMappingFiles(myMappingFiles);
        for (Category category : categories) {
            CategoryVO categoryVO = mapper.map(category, CategoryVO.class);
            categoryVOList.add(categoryVO);
        }
        return ResponseEntity.ok().body(categoryVOList);
    }

}
