package com.armapp.service;

import com.armapp.exception.InvalidIdException;
import com.armapp.model.Project;
import com.armapp.model.Talent;
import com.armapp.model.TalentProject;
import com.armapp.repository.TalentRepository;
import com.armapp.vo.TalentVO;
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
public class TalentServiceImpl implements ITalentService {

    private TalentRepository talentRepository;

    @Autowired
    public void setTalentRepo(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    /**
     * @param talents
     * @author - AkashKanaparthi
     */
    @Override
    public void addTalent(Set<Talent> talents) {
        talentRepository.saveAll(talents);
    }

    /**
     * @param talent
     * @author - AkashKanaparthi
     */
    @Override
    public void updateTalent(Talent talent) {
        Talent talent1 = talentRepository.findById(talent.getTalentId()).get();
        talent1.setUpdatedAt(LocalDateTime.now());
        talentRepository.save(talent1);
    }

    /**
     * @param talentId
     * @throws InvalidIdException
     * @author - AkashKanaparthi
     */
    @Override
    public void deleteTalent(int talentId) throws InvalidIdException {
        Talent talent = talentRepository.findById(talentId).get();
        talent.setDeleted(true);
        talentRepository.save(talent);
    }

    /**
     * @param talentId
     * @return
     * @throws InvalidIdException
     * @author - AkashKanaparthi
     */
    @Override
    public Talent getById(int talentId) throws InvalidIdException {
        return talentRepository.findById(talentId).get();
    }

    /**
     * @return
     * @author - AkashKanaparthi
     */
    @Override
    public List<Talent> getAll() {
        return talentRepository.findAll()
                .stream()
                .filter(project -> !project.isDeleted())
                .sorted(Comparator.comparing(Talent::getTalentName))
                .collect(Collectors.toList());
    }


    @Override
    public List<Talent> getByTalentNameLike(String keyword) {
        return talentRepository.findByTalentNameLike("%"+keyword+"%")
                .stream()
                .filter(project -> !project.isDeleted())
                .sorted(Comparator.comparing(Talent::getTalentName))
                .collect(Collectors.toList());
    }


}

