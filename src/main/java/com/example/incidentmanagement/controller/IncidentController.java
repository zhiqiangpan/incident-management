package com.example.incidentmanagement.controller;

import com.example.incidentmanagement.exception.IncidentAlreadyExistsException;
import com.example.incidentmanagement.exception.IncidentNotFoundException;
import com.example.incidentmanagement.pojo.dto.IncidentModifyDTO;
import com.example.incidentmanagement.pojo.dto.IncidentSaveDTO;
import com.example.incidentmanagement.pojo.model.Incident;
import com.example.incidentmanagement.service.IncidentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author panzhiqiang
 * @Description
 * @Date 2024/11/28
 */
@RestController
@RequestMapping("/api/incidents")
@Validated
public class IncidentController {

    private final IncidentService incidentService;

    @Autowired
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public ResponseEntity<Incident> createIncident(@Valid @RequestBody IncidentSaveDTO incidentSaveDTO) {
        try {
            Incident incident = new Incident();
            BeanUtils.copyProperties(incidentSaveDTO, incident);
            Incident createdIncident = incidentService.createIncident(incident);
            return new ResponseEntity<>(createdIncident, HttpStatus.CREATED);
        } catch (IncidentAlreadyExistsException e) { 
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        Optional<Incident> incident = incidentService.getIncidentById(id);
        if (incident.isPresent()) {
            return ResponseEntity.ok(incident.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Incident> modifyIncident(@PathVariable Long id, @Valid @RequestBody IncidentModifyDTO incidentModifyDTO) {
        try {
            Incident incident = new Incident();
            BeanUtils.copyProperties(incidentModifyDTO, incident);
            Incident updatedIncident = incidentService.modifyIncident(id, incident);
            return ResponseEntity.ok(updatedIncident);
        } catch (IncidentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncidentById(@PathVariable Long id) {
        try{
            incidentService.deleteIncidentById(id);
            return ResponseEntity.ok().build();
        }catch (IncidentNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }
}
