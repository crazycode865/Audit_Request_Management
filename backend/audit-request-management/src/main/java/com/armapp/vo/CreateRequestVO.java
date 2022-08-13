/**
 * @Author : Sipoy Sikindar Jillepally
 * @Date : 19-07-2022
 * @Time : 18:25
 * @Project Name : audit-request-management
 */
package com.armapp.vo;

import com.armapp.model.RequestSchedule;
import com.armapp.model.Task;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateRequestVO {

    private Integer requestId;
    private String productionName;
    private String productionNumber;
    private String projectName;
    private String talentName;
    private String unionName;
    private String priority;
    private RequestSchedule requestSchedule;
    private String status;
    private LocalDate contractDate;
    private Set<Task> tasksList;
    private String createdBy;
    private String contractNo;
}
