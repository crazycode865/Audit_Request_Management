package com.armapp.service;

import com.armapp.exception.InvalidIdException;
import com.armapp.model.Talent;
import com.armapp.model.TalentProject;
import com.armapp.vo.TalentVO;

import java.util.List;
import java.util.Set;

/**
 * @author - Akash Kanaparthi
 * @date - 08-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
public interface ITalentService {

    void addTalent(Set<Talent> talent);

    void updateTalent(Talent talent);

    void deleteTalent(int talentId) throws InvalidIdException;

    Talent getById(int talentId) throws InvalidIdException;

    List<Talent> getAll();

    List<Talent> getByTalentNameLike(String keyword);
}
