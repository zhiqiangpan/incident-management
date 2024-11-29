package com.example.incidentmanagement.pojo.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author panzhiqiang
 * @Description 事件创建DTO
 * @Date 2024/11/28
 */
@Data
public class IncidentSaveDTO {
    @NotNull
    @NotEmpty(message = "name cannot be empty")
    private String name;
    
    private String description;

    @NotNull
    @NotEmpty(message = "Status cannot be empty")
    private String status;
}
