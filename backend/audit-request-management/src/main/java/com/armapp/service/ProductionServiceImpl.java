package com.armapp.service;

import com.armapp.exception.InvalidIdException;
import com.armapp.model.Production;
import com.armapp.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProductionServiceImpl implements IProductionService{

    private ProductionRepository productionRepository;
    @Autowired
    public void setProductionRepo(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    /**
     * @author - Akash Kanaparthi
     * @param productions
     *
     */
    @Override
    public void addProduction(Set<Production> productions) {
        productionRepository.saveAll(productions);
    }

    /**
     *
     * @author - AkashKanaparthi
     * @param production
     */
    @Override
    public void updateProduction(Production production) {
        Production production1 = productionRepository.findById(production.getProductionId()).get();
        production1.setUpdatedAt(LocalDateTime.now());
        productionRepository.save(production1);
    }

    /**
     *
     * @author - AkashKanaparthi
     * @param productionId
     * @throws InvalidIdException
     */
    @Override
    public void deleteProduction(int productionId) throws InvalidIdException {
        Production production = productionRepository.findById(productionId).get();
        production.setDeleted(true);
        productionRepository.save(production);
    }

    /**
     *
     * @author - AkashKanaparthi
     * @param productionId
     * @return Production Object
     * @throws InvalidIdException
     */
    @Override
    public Production getById(int productionId) throws InvalidIdException{
        return productionRepository.findById(productionId).get();
    }

    /**
     *
     * @author - AkashKanaparthi
     * @return List of all Production Companies
     */
    @Override
    public List<Production> getAll() {
        return productionRepository.findAll()
                .stream()
                .filter(production -> !production.isDeleted())
                .sorted(Comparator.comparing(Production::getProductionCompanyName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Production> getByProductionCompanyNameLike(String companyName) {
        return productionRepository.findByProductionCompanyNameLike("%" + companyName + "%")
                .stream()
                .filter(production -> !production.isDeleted())
                .sorted(Comparator.comparing(Production::getProductionCompanyName))
                .collect(Collectors.toList());
    }
}
