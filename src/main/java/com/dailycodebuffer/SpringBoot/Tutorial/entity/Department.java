package com.dailycodebuffer.SpringBoot.Tutorial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    /* public Department(Long departmentId, String departmentName, String departmentAddress, String departmentCode){
        this.departmentAddress = departmentAddress;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
    }
    public Department(){

    } */

    @Override
    public String toString() {
        // convert class objects to string
        return super.toString();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "The department Name is required")
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
   

   /*  public Long getDepartmentId() {
        return departmentId;
    }
    public String getDepartmentCode() {
        return departmentCode;
    }
    public String getDepartmentAddress() {
        return departmentAddress;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentId(Long departmenId) {
        this.departmentId = departmenId;
    }
    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    } */
}