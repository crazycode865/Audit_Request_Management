package com.armapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author - Akash Kanaparthi
 * @date - 05-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Request implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;
    //    @Enumerated(EnumType.STRING)
    private String priority;
    //    @Enumerated(EnumType.STRING)
    private String unionName;
    //    @Enumerated(EnumType.STRING)
    private String status;
    // TODO these should be associations not string values
    // Do this in future
    private String productionName;
    private String projectName;
    private String talentName;
    private LocalDate requestCreatedDate;
    private LocalDate contractDate;

    private String contractNo;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL)
    private RequestSchedule requestSchedule;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private Set<Task> tasksList;

    @OneToMany(mappedBy = "request")
    @JsonIgnore
    private Set<Assets> assets;
    // your list page should be based on this col value
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

}
