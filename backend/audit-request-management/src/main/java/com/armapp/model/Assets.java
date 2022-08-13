package com.armapp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Dibya Prakash Ojha
 * @date : 21-Jul-22
 * @project : audit-request-management
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Assets implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assetId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    private String assetName;

    private LocalDateTime createdAt;

    private boolean isDeleted;

}
