package com.example.incidentmanagement.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author panzhiqiang
 * @Description 事件实体
 * @Date 2024/11/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incident {
    private Long id;
    private String name;
    private String description;
    private String status;
}
