package com.dailycodebuffer.SpringBoot.Tutorial.controller;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Department;
import com.dailycodebuffer.SpringBoot.Tutorial.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @MockBean 
    private DepartmentService departmentService;
  
    @Autowired
    ObjectMapper mapper;

    private Department department;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp(){
        department = Department.builder().departmentAddress("jdjdjd").departmentName("EEE").departmentCode("EEE").departmentId(1L).build();
    }

    @Test
    void testDeleteDepartmentById() {

    }

    @Test
    void testFetchDeparments() {

    }

    @Test
    void testFetchDepartmentByName() {

    }

    @Test
    void testFindDepartment() throws Exception {
        Mockito.when(departmentService.findDepartment(1L)).thenReturn(department);
        
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/departments/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$",  notNullValue())).andExpect(jsonPath("$.departmentName", is("EEE")));;
    }

    @Test
    void testSaveDepartment() throws Exception {
        Department inputDepartment = Department.builder()

        .departmentAddress("jdjdjd").departmentName("EEE").departmentCode("EEE").build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/departments")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(this.mapper.writeValueAsString(inputDepartment));
      
        mockMvc.perform(mockRequest)
        .andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
        .andExpect(jsonPath("$.departmentName", is("EEE")));
    }   


    @Test
    void testUpdateDepartment() {

    }
}
