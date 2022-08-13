package com.armapp.service;

import com.armapp.model.ReqID;
import com.armapp.repository.ReqIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReqIDServiceImpl implements IReqIDService {

    @Autowired
    private ReqIDRepository reqIDRepository;

    @Override
    public ReqID createReqID(ReqID reqID) {
        return reqIDRepository.save(reqID);
    }
}
