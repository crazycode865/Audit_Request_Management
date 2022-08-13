package com.armapp.service;

import com.armapp.exception.InvalidIdException;
import com.armapp.model.Category;

import java.util.List;
import java.util.Set;

/**
 * @author - Akash Kanaparthi
 * @date - 08-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */

public interface ICategoryService {

    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int categoryId) throws InvalidIdException;
    Category getById(int categoryId) throws InvalidIdException;

    List<Category> getAll();


}
