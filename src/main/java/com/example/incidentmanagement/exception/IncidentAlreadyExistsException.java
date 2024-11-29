package com.example.incidentmanagement.exception;

/**
 * @author panzhiqiang
 * @Description
 * @Date 2024/11/28
 */
public class IncidentAlreadyExistsException extends RuntimeException {
    public IncidentAlreadyExistsException(String message) {
        super(message);
    }
}
