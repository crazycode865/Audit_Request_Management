package com.armapp.service;

import com.armapp.exception.InvalidIdException;
import com.armapp.model.Production;

import java.util.List;
import java.util.Set;

/**
 *@author - Akash Kanaparthi
 * @date - 08-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
public interface IProductionService {
    
    void addProduction(Set<Production> production);
    void updateProduction(Production production);
    void deleteProduction(int productionId) throws InvalidIdException;
    Production getById(int productionId) throws InvalidIdException;

    List<Production> getAll();

    List<Production> getByProductionCompanyNameLike(String companyName);


}
