package com.dailycodebuffer.SpringBoot.Tutorial.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Department;
import com.dailycodebuffer.SpringBoot.Tutorial.respository.DepartmentRepository;

@SpringBootTest
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp(){
        Department department = Department.builder().
        departmentName("IT")
        .departmentAddress("Ahmed")
        .departmentCode("IT-06")
        .departmentId(1L).build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);
    }

    @Test
    public void testDeleteDepartmentById() {

    }

    @Test
    @DisplayName("Get data based on valid department name")
    public void testFetchDeparmentByName() {
        String departmentName = "IT";
        Department found = departmentService.fetchDeparmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test
    @Disabled
    void testFetchDeparments() {

    }

    @Test
    @Disabled
    void testFindDepartment() {

    }

    @Test
    @Disabled
    void testSaveDepartment() {

    }

    @Test
    @Disabled 
    void testUpdateDepartment() {

    }
}
