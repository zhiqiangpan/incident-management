package com.example.incidentmanagement.service;

import com.example.incidentmanagement.exception.IncidentAlreadyExistsException;
import com.example.incidentmanagement.exception.IncidentNotFoundException;
import com.example.incidentmanagement.pojo.model.Incident;
import com.example.incidentmanagement.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author panzhiqiang
 * @Description 事件服务类
 * @Date 2024/11/28
 */
@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }
    
    public Incident createIncident(Incident incident) {
        // 假设Incident有一个唯一约束的字段，比如name
        // 这里我们检查是否已经存在一个具有相同name的事件
        Optional<Incident> existingIncident = incidentRepository.findByName(incident.getName());
        if (existingIncident.isPresent()) {
            throw new IncidentAlreadyExistsException("Incident with this name already exists");
        }
        return incidentRepository.save(incident);
    }

    @Cacheable(value = "incidents", key = "#id")
    public Optional<Incident> getIncidentById(Long id) {
        return incidentRepository.findById(id);
    }

    @CacheEvict(value = "incidents", key = "#id")
    public Incident modifyIncident(Long id, Incident incidentDetails) {
        Optional<Incident> incident = incidentRepository.findById(id);
        if (incident.isPresent()) {
            Incident updatedIncident = incident.get();
            updatedIncident.setName(incidentDetails.getName());
            updatedIncident.setStatus(incidentDetails.getStatus());
            updatedIncident.setDescription(incidentDetails.getDescription());
            return incidentRepository.save(updatedIncident);
        } else {
            throw new IncidentNotFoundException("Incident not found");
        }
    }
    
    @CacheEvict(value = "incidents", key = "#id")
    public void deleteIncidentById(Long id) {
        Optional<Incident> incident = incidentRepository.findById(id);
        if (incident.isPresent()) {
            incidentRepository.deleteById(id);
        } else {
            throw new IncidentNotFoundException("Incident not found");
        }
    }
    
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }
}
