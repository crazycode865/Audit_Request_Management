package com.armapp.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageVO {
    private Integer messageId;
    private Integer taskId;
    private String from;
    private String fromUserName;
    private String to;
    private String toUserName;
    private LocalDateTime createdAt;
    private String messageText;
    private boolean isDeleted;
    private boolean isSeen;

}
