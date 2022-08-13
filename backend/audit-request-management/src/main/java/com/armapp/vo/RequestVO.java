package com.armapp.vo;

import com.armapp.model.Priority;
import com.armapp.model.Status;
import com.armapp.model.Union;
import lombok.*;

/**
 * @author - Akash
 * @date - 05-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestVO {


    private Integer requestId;
    private String priority;
    private String union;
    private String status;
    private String productionCompanyName;
    private String projectName;
    private String talentName;
    private RequestScheduleVO requestSchedule;
    private String contractNo;
    private boolean assetExists;
//    private Set<Task> tasks;

}
