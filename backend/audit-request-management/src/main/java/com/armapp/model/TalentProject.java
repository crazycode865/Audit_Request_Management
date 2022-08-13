

package com.armapp.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name = "talent_project")

@AssociationOverrides({
        @AssociationOverride(name = "pk.talent",
                joinColumns = @JoinColumn(name = "talent_id")),
        @AssociationOverride(name = "pk.project",
                joinColumns = @JoinColumn(name = "project_id")) })

public class TalentProject implements Serializable {


    private TalentProjectId pk = new TalentProjectId();
    private String contractNumber;

    public TalentProject() {
    }

    @EmbeddedId
    public TalentProjectId getPk() {
        return pk;
    }

    public void setPk(TalentProjectId pk) {
        this.pk = pk;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    @Transient
    public Talent getTalent() {
        return getPk().getTalent();
    }

    public void setTalent(Talent talent) {
        getPk().setTalent(talent);
    }


    @Transient
    public Project getProject() {
        return getPk().getProject();
    }

    public void setProject(Project project) {
        getPk().setProject(project);
    }


}

