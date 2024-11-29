package com.example.incidentmanagement.repository;

import com.example.incidentmanagement.pojo.model.Incident;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author panzhiqiang
 * @Description 事件存储
 * @Date 2024/11/28
 */
@Repository
public class IncidentRepository {
    private static final AtomicLong counter = new AtomicLong();
    private final List<Incident> incidents = new ArrayList<>();

    public Incident save(Incident incident) {
        if (incident.getId() == null) {
            incident.setId(counter.incrementAndGet());
        }
        incidents.removeIf(i -> i.getId().equals(incident.getId()));
        incidents.add(incident);
        return incident;
    }

    public Optional<Incident> findById(Long id) {
        return incidents.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        incidents.removeIf(i -> i.getId().equals(id));
    }

    public List<Incident> findAll() {
        return new ArrayList<>(incidents);
    }

    public Optional<Incident> findByName(String name) {
        return incidents.stream().filter(i -> i.getName().equals(name)).findFirst();
    }
}
