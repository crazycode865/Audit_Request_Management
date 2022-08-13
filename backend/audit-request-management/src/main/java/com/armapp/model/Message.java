package com.armapp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer messageId;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    @OneToOne
    @JoinColumn(name = "message_from")
    private Owner messageFrom;
    @OneToOne
    @JoinColumn(name = "message_to")
    private Owner messageTo;
    private LocalDateTime createdAt;
    private String messageText;
    private boolean isDeleted;
    private boolean isSeen;

}
