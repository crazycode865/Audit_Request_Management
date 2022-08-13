package com.armapp.model;

/**
 * @author - Akash Kanaparthi
 * @date - 05-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
public enum Status {
    PI("Pending Internal"),
    PT("Pending Talent"),
    SP("Settlement Processing"),
    COMPLETED("Completed");

    private final String status ;

    Status(String status) {
        this.status = status;
    }
}
