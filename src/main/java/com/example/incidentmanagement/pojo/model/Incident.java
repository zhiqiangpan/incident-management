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
    /**
     * 事件id
     */
    private Long id;

    /**
     * 事件名称
     */
    private String name;

    /**
     * 事件描述
     */
    private String description;

    /**
     * 事件状态
     */
    private String status;
}
