package com.cbfacademy.apiassessment.model.entities;

import java.util.List;

import com.cbfacademy.apiassessment.model.enums.Status;

public class Issue {

    private long id;
    private String title;
    private String description;
    private Status status;
    private List<String> assignedTo;
    private List<String> createdBy;

    // Constructors
    public Issue(long id, String title, String description, Status status, List<String> assignedTo,
            List<String> createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
    }

    public Issue() {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(List<String> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<String> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<String> createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assignedTo=" + assignedTo +
                ", createdBy=" + createdBy +
                '}';
    }

}
