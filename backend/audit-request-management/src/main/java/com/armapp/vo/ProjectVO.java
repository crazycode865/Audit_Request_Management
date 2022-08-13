package com.armapp.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectVO {

    private String projectName;
    private List<TalentVO> talentVOList;

}
