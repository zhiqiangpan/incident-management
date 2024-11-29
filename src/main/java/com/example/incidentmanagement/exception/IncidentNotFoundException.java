package com.example.incidentmanagement.exception;

/**
 * @author panzhiqiang
 * @Description
 * @Date 2024/11/28
 */
public class IncidentNotFoundException extends RuntimeException {
    public IncidentNotFoundException(String message) {
        super(message);
    }
}
