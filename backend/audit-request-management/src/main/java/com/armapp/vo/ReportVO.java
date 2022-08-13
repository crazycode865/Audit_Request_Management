package com.armapp.vo;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportVO {

    private LocalDate audit_start_date;
    private LocalDate audit_end_date;

}