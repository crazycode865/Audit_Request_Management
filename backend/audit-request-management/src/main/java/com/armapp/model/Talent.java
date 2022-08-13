

package com.armapp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
public class Talent implements Serializable {

    @Id
    @Column(name = "talent_id")
    private Integer talentId;
    private String talentName;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.talent", cascade=CascadeType.ALL)
    private Set<TalentProject> talentProjects;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

}
