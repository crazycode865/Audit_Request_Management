package com.armapp.model;

import lombok.*;

import javax.persistence.*;
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
public class Production {

    @Id
    @Column(name = "production_id")
    private Integer productionId;
    private String productionCompanyName;
    private String contractNumber;

    @OneToMany(mappedBy="production", cascade={CascadeType.ALL})
    @ToString.Exclude
    private Set<Project> projects;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
