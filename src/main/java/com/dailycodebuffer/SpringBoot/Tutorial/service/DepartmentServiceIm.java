package com.dailycodebuffer.SpringBoot.Tutorial.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.dailycodebuffer.SpringBoot.Tutorial.Exception.RequestNotFoundException;
import com.dailycodebuffer.SpringBoot.Tutorial.entity.Department;
import com.dailycodebuffer.SpringBoot.Tutorial.respository.DepartmentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceIm implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

   private Logger logger = LoggerFactory.getLogger(Department.class);
    @Override
    public Department saveDepartment(Department department) {
        // Save department
        logger.info("inside save department");
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDeparments() {
        // fetch all departments
        logger.info("inside fetch department");
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartment(Long departmentId) throws RequestNotFoundException {
        // find department by id
        logger.info("inside find department");
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new RequestNotFoundException("Department not found");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        // delete department by id
         departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        // update department by id
        logger.info("inside update adepartment");
        Department dbUpd = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())){
            dbUpd.setDepartmentName(department.getDepartmentName());
        }  
        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())){
            dbUpd.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            dbUpd.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepository.save(dbUpd);

    }

    @Override
    public Department fetchDeparmentByName(String departmentName) {
        // fetch department by name
        logger.info("inside delete department");
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }


    
    

}
