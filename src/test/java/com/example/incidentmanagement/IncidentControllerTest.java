package com.example.incidentmanagement;

import com.example.incidentmanagement.controller.IncidentController;
import com.example.incidentmanagement.pojo.dto.IncidentModifyDTO;
import com.example.incidentmanagement.pojo.dto.IncidentSaveDTO;
import com.example.incidentmanagement.pojo.model.Incident;
import com.example.incidentmanagement.service.IncidentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author panzhiqiang
 * @Description
 * @Date 2024/11/28
 */
@SpringBootTest
@AutoConfigureMockMvc
public class IncidentControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private IncidentService incidentService;

    @Autowired
    private IncidentController incidentController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(incidentController).build();
    }

    @Test
    public void testCreateIncident_Success() throws Exception {
        IncidentSaveDTO incidentSaveDTO = new IncidentSaveDTO();
        incidentSaveDTO.setName("TestIncidentCreate");
        incidentSaveDTO.setDescription("test incident create");
        incidentSaveDTO.setStatus("Open");
        
        String incidentSaveDTOJson = objectMapper.writeValueAsString(incidentSaveDTO);
        mockMvc.perform(post("/api/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(incidentSaveDTOJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("TestIncidentCreate"))
                .andExpect(jsonPath("$.description").value("test incident create"))
                .andExpect(jsonPath("$.status").value("Open"))
        ;
    }

    @Test
    public void testCreateIncident_AlreadyExists() throws Exception {
        Incident incident = new Incident();
        incident.setName("TestIncidentCreateAlreadyExists");
        incident.setDescription("test incident create already exists");
        incident.setStatus("Open");
        incidentService.createIncident(incident);
        
        IncidentSaveDTO incidentSaveDTO = new IncidentSaveDTO();
        incidentSaveDTO.setName("TestIncidentCreateAlreadyExists");
        incidentSaveDTO.setDescription("test incident create already exists");
        incidentSaveDTO.setStatus("Open");

        String incidentSaveDTOJson = objectMapper.writeValueAsString(incidentSaveDTO);
        mockMvc.perform(post("/api/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(incidentSaveDTOJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void testGetIncidentById_Success() throws Exception {
        Incident incident = new Incident(1L, "Test Incident","This is a test incident", "Open");
        incidentService.createIncident(incident);

        mockMvc.perform(get("/api/incidents/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Incident"))
                .andExpect(jsonPath("$.description").value("This is a test incident"))
                .andExpect(jsonPath("$.status").value("Open"));

    }
    
    @Test
    public void testGetIncidentById_NotFound() throws Exception {
        Long incidentId = 2L;
        mockMvc.perform(get("/api/incidents/{id}", incidentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void testModifyIncident_Success() throws Exception {
        Incident incident = new Incident(20L, "testModifyIncidentSuccess","test delete incident", "Open");
        incidentService.createIncident(incident);

        IncidentModifyDTO incidentModifyDTO = new IncidentModifyDTO();
        incidentModifyDTO.setId(20L);
        incidentModifyDTO.setName("testModifyIncidentSuccess_2");
        incidentModifyDTO.setDescription("test delete incident 2");
        incidentModifyDTO.setStatus("Close");
        
        String incidentModifyDTOJson = objectMapper.writeValueAsString(incidentModifyDTO);
        mockMvc.perform(put("/api/incidents/{id}", 20L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(incidentModifyDTOJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(20L))
                .andExpect(jsonPath("$.name").value("testModifyIncidentSuccess_2"))
                .andExpect(jsonPath("$.description").value("test delete incident 2"))
                .andExpect(jsonPath("$.status").value("Close"))
        ;
    }

    @Test
    public void testModifyIncident_NotFound() throws Exception {
        Long incidentId = 31L;
        IncidentModifyDTO incidentModifyDTO = new IncidentModifyDTO();
        incidentModifyDTO.setId(incidentId);
        incidentModifyDTO.setName("testModifyIncidentNotFound");
        incidentModifyDTO.setDescription("test modify incident not found");
        incidentModifyDTO.setStatus("Close");

        String incidentModifyDTOJson = objectMapper.writeValueAsString(incidentModifyDTO);
        mockMvc.perform(put("/api/incidents/{id}", incidentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(incidentModifyDTOJson))
                .andExpect(status().isNotFound())
        ;
    }
    
    @Test
    public void testDeleteIncidentById_Success() throws Exception {
        Incident incident = new Incident(10L, "TestDeleteIncident","test delete incident", "Open");
        incidentService.createIncident(incident);

        mockMvc.perform(delete("/api/incidents/10"))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteIncidentById_NotFound() throws Exception {
        Long incidentId = 11L;
        mockMvc.perform(delete("/api/incidents/{id}", incidentId))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetAllIncidents_Success() throws Exception {
        List<Incident> allIncidents = incidentService.getAllIncidents();

        mockMvc.perform(get("/api/incidents")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(allIncidents.size()))
        ;
    }

}
