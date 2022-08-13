package com.armapp.service;

import com.armapp.exception.InvalidIdException;
import com.armapp.model.Category;
import com.armapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author - Akash Kanaparthi
 * @date - 08-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
@Service
public class CategoryServiceImpl implements ICategoryService{

    private CategoryRepository categoryRepository;
    @Autowired
    public void setCategoryRepo(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    /**
     * adding the categories to the database
     * @author - Akash Kanaparthi
     * @param category
     */
    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    /**
     * updating the category by taking complete category class
     * and setting the updated time
     * @author - Akash Kanaparthi
     * @param category
     */
    @Override
    public void updateCategory(Category category) {
        Category category1 = categoryRepository.findById(category.getCategoryId()).get();
        category1.setUpdatedBy(System.getProperty("user.name"));
        category1.setUpdatedAt(LocalDateTime.now(Clock.systemDefaultZone()));
        categoryRepository.save(category1);
    }
    /**
     * this method used to delete the category by
     * changing the boolean isDeleted to true
     * @author - Akash Kanaparthi
     * @param categoryId
     */
    @Override
    public void deleteCategory(int categoryId) throws InvalidIdException {
        Category category = categoryRepository.findById(categoryId).get();
        if (category == null){
            throw new InvalidIdException("Id Not Found");
        }
        category.setDeleted(true);
        categoryRepository.save(category);
    }
    /**
     *this method use to get one category object data
     * @author - Akash Kanaparthi
     * @param categoryId
     * @return Category object
     */
    @Override
    public Category getById(int categoryId) throws InvalidIdException{
        Category category = categoryRepository.findById(categoryId).get();
        if (category == null){
            throw new InvalidIdException("Id Not Found");
        }
        return category;
    }

    /**
     * @author - Akash Kanaparthi
     * @return List of Categories
     */
    @Override
    public List<Category> getAll() {
        return categoryRepository
                .findAll()
                .stream()
                .filter(category -> !category.isDeleted())
                .sorted(Comparator.comparing(Category::getReportType))
                .collect(Collectors.toList());
    }

}
