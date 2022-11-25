package com.ideas2it.EmployeeManagementSystem.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Component
@Scope(value = "prototype")
public class ProjectDTO {

    private int projectId;

    private String projectName;

    private String domain;

    private String clientName;

    private String clientEmaiId;

    private LocalDate startDate;

    private LocalDate dueDate;

    private LocalDate endDate;
    
    private List<EmployeeDTO> employee;
    
    public ProjectDTO() {
    }

    public ProjectDTO(String projectName, String domain,
                      String clientName, String clientEmaiId,
                      LocalDate startDate, LocalDate dueDate,
                      LocalDate endDate, List<EmployeeDTO> employee) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.domain = domain;
        this.clientName = clientName;
        this.clientEmaiId = clientEmaiId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.endDate = endDate;
        this.employee = employee;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int  getProjectId() {
        return projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String  getProjectName() {
        return projectName;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String  getDomain() {
        return domain;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String  getClientName() {
        return clientName;
    }

    public void setClientEmailId(String clientEmaiId) {
        this.clientEmaiId = clientEmaiId;
    }

    public String  getClientEmailId() {
        return clientEmaiId;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate  getStartDate() {
        return startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate  getDueDate() {
        return dueDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate  getEndDate() {
        return endDate;
    }

    public void setEmployee(List<EmployeeDTO> employee) {
        this.employee = employee;
    }

    public List<EmployeeDTO> getEmployee() {
        return employee;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n Project Details \n")
               .append("\n Project ID          : ").append(this.getProjectId())
               .append("\n Project Name        : ").append(this.getProjectName())
               .append("\n Domain              : ").append(this.getDomain())
               .append("\n ClientName          : ").append(this.getClientName())
               .append("\n Client EmailID      : ").append(this.getClientEmailId())
               .append("\n Project Start Date  : ").append(this.getStartDate())
               .append("\n Project Due Date    : ").append(this.getDueDate())
               .append("\n Project End Date    : ").append(this.getEndDate())
               .append(this.getEmployee());
        return builder.toString();
    }    
}