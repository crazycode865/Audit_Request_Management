package com.armapp.vo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestScheduleVO {

    private LocalDate requestCreated;
    private LocalDate expectedClosure;

}
