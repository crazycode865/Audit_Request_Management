package com.armapp.service;

import com.armapp.model.Message;

import java.util.List;

public interface IMessageService {
    List<Message> findAll();

    Message saveMessage(Message message);

    List<Message> findAllByTaskId(Integer taskId);
}
