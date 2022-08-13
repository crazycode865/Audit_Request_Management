package com.armapp.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductionVO {

    private Integer productionId;
    private String productionCompanyName;
    private String contractNumber;

    public ProductionVO(Integer productionId, String productionCompanyName) {
        this.productionId = productionId;
        this.productionCompanyName = productionCompanyName;
    }
}
