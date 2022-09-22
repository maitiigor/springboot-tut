package com.dailycodebuffer.SpringBoot.Tutorial.respository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.dailycodebuffer.SpringBoot.Tutorial.entity.Department;

@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp(){
        Department department = Department.builder()

        .departmentAddress("jdjdjd").departmentName("EEE").departmentCode("EEE").departmentId(1L).build();
        entityManager.persist(department);
    }

    @Test
    @Disabled
    void testFindByDepartmentName() {   
       
    }

    @Test
    public void whenFindById_thenReturnDepartment(){
        Department department = departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(), "EEE");
    }

    @Test
    void testFindByDepartmentNameIgnoreCase() {

    }
}
