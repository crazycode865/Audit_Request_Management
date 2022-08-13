package com.armapp.controller;

import com.armapp.model.Message;
import com.armapp.model.Owner;
import com.armapp.model.Task;
import com.armapp.repository.OwnerRepository;
import com.armapp.repository.TaskRepository;
import com.armapp.service.IMessageService;
import com.armapp.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TaskRepository taskRepository;

    public MessageController() {
    }


    @GetMapping("/messages/{taskId}")
        // http://localhost:9090/messages/1
    ResponseEntity<List<MessageVO>> messagesByTaskId(@PathVariable("taskId") String taskId) {
        List<Message> messages = iMessageService.findAllByTaskId(Integer.valueOf(taskId));
        List<MessageVO> messagesList = new ArrayList<>();
        for (Message savedMsg : messages) {
            MessageVO msgVo = new MessageVO();
            msgVo.setMessageId(savedMsg.getMessageId());
            msgVo.setCreatedAt(savedMsg.getCreatedAt());
            msgVo.setMessageText(savedMsg.getMessageText());
            msgVo.setFrom(savedMsg.getMessageFrom().getOwnerId().toString());
            msgVo.setFromUserName(savedMsg.getMessageFrom().getOwnerUserId());
            msgVo.setTo(savedMsg.getMessageTo().getOwnerId().toString());
            msgVo.setToUserName(savedMsg.getMessageTo().getOwnerUserId());
            msgVo.setTaskId(savedMsg.getTask().getTaskId());
            msgVo.setSeen(savedMsg.isSeen());
            messagesList.add(msgVo);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "all messages for a task"))
                .body(messagesList);
    }


    @GetMapping("/messages")
        // Not in use
    ResponseEntity<List<MessageVO>> messages() {
        List<Message> savedMsgs = iMessageService.findAll();
        //TODO As of now just return one
        Message savedMsg = savedMsgs.get(0);
        List<MessageVO> messages = new ArrayList<>();
        MessageVO msgVo = new MessageVO();
        msgVo.setMessageId(savedMsg.getMessageId());
        msgVo.setCreatedAt(savedMsg.getCreatedAt());
        msgVo.setMessageText(savedMsg.getMessageText());
        msgVo.setFrom(savedMsg.getMessageFrom().getOwnerId().toString());
        msgVo.setFromUserName(savedMsg.getMessageFrom().getOwnerName());
        msgVo.setTo(savedMsg.getMessageTo().getOwnerId().toString());
        msgVo.setToUserName(savedMsg.getMessageTo().getOwnerName());
        msgVo.setTaskId(savedMsg.getTask().getTaskId());
        msgVo.setSeen(savedMsg.isSeen());
        messages.add(msgVo);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "all messages"))
                .body(messages);
    }

    @PostMapping("/create/message")
    //http://localhost:9090/create/message
    //    {"taskId": "1",
    //            "from": "11",
    //            "to":"10",
    //            "messageText":"Sample message"}
    public ResponseEntity<MessageVO> createMessage(@RequestBody MessageVO messageVO) {
        Owner from=ownerRepository.getByOwnerUserId(messageVO.getFromUserName());
        Owner to=ownerRepository.getByOwnerUserId(messageVO.getToUserName());
        Task task = taskRepository.findById(messageVO.getTaskId()).get();
        Message message = new Message();
        // message.setMessageId(123);
        message.setMessageTo(to);
        message.setMessageFrom(from);
        message.setCreatedAt(LocalDateTime.now());
        message.setMessageText(messageVO.getMessageText());
        message.setTask(task);
        Message savedMsg = iMessageService.saveMessage(message);

        MessageVO msgVo = new MessageVO();
        msgVo.setMessageId(savedMsg.getMessageId());
        msgVo.setCreatedAt(savedMsg.getCreatedAt());
        msgVo.setMessageText(savedMsg.getMessageText());
        msgVo.setFrom(savedMsg.getMessageFrom().getOwnerId().toString());
        msgVo.setFromUserName(savedMsg.getMessageFrom().getOwnerUserId());
        msgVo.setTo(savedMsg.getMessageTo().getOwnerId().toString());
        msgVo.setToUserName(savedMsg.getMessageTo().getOwnerUserId());
        msgVo.setTaskId(savedMsg.getTask().getTaskId());
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders
                        .add("desc", "all messages"))
                .body(msgVo);
    }
}
