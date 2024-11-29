package com.example.incidentmanagement.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author panzhiqiang
 * @Description
 * @Date 2024/11/28
 */
@Data
public class IncidentModifyDTO {
    
    @NotNull(message = "Incident ID cannot be null")
    private Long id;
    
    @NotNull
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    
    private String description;

    @NotNull
    @NotEmpty(message = "Status cannot be empty")
    private String status;
}
