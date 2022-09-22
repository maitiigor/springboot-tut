package com.dailycodebuffer.SpringBoot.Tutorial.service;

import java.util.List;

import com.dailycodebuffer.SpringBoot.Tutorial.Exception.RequestNotFoundException;
import com.dailycodebuffer.SpringBoot.Tutorial.entity.Department;

public interface DepartmentService {

    public Department saveDepartment(Department department);

    public List<Department> fetchDeparments();

    public Department findDepartment(Long departmentId) throws RequestNotFoundException;

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

    public Department fetchDeparmentByName(String departmentName);

    
}
