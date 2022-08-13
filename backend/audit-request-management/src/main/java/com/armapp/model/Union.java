package com.armapp.model;

/**
 * @author - Akash Kanaparthi
 * @date - 05-07-2022
 * @project - Acheron-Training-AUDIT-REQUEST-MANAGEMENT-BACKEND
 */
public enum Union {
    DAG("DAG"),
    WAG("WAG"),
    SAG("SAG-AFTRA"),
    INDEPENDENT("NA");

    private final String type;

    Union(String type) {
        this.type = type;
    }
}
