package com.armapp.vo;

import com.armapp.model.Category;
import com.armapp.model.Priority;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskVO {

    private int requestId;
    private Integer taskId;
    private String taskDescription;
    private String productionCompanyName;
    private String projectName;
    private String talentName;
    private String priority;
    private LocalDate auditStartDate;
    private LocalDate auditEndDate;
    private LocalDate requestRaised;
    private LocalDate requestClosed;
    private String contractNo;
    private String taskCreator;
    private String reportOwner;
    private String taskCreatorFullName;
    private String reportOwnerFullName;
    private boolean assetExists;
}
