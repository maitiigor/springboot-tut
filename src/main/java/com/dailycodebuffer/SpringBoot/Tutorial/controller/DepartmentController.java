package com.dailycodebuffer.SpringBoot.Tutorial.controller;

import java.util.List;

import javax.validation.Valid;

import com.dailycodebuffer.SpringBoot.Tutorial.Exception.RequestNotFoundException;
import com.dailycodebuffer.SpringBoot.Tutorial.entity.Department;
import com.dailycodebuffer.SpringBoot.Tutorial.service.DepartmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDeparments(){

        return departmentService.fetchDeparments();
        
    }
 
    @GetMapping("/departments/{id}")
    public Department findDepartment(@PathVariable("id") Long departmentId) throws RequestNotFoundException{

        return departmentService.findDepartment(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        
        return "Department deleted successfully"; 
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return departmentService.updateDepartment(departmentId, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDeparmentByName(departmentName);
    }
}
