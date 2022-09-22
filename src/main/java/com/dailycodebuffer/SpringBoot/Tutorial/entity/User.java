package com.dailycodebuffer.SpringBoot.Tutorial.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.dailycodebuffer.SpringBoot.Tutorial.service.UserService;
import com.dailycodebuffer.SpringBoot.Tutorial.validator.Unique;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

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
    private Long id;

    @NotBlank(message = "The first name field is required")
    private String firstName;
    @NotBlank (message="The last name field is required")
    private String lastName;

    @NotBlank(message = "The password field is required")
    private String password;
    
   
    @Unique(service = UserService.class, fieldName = "email", message = "The email has already already exist")
    @NotBlank(message = "The email field is required")
    private String email;
   
    @ManyToMany
    private Collection<Role> roles = new ArrayList<>();

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