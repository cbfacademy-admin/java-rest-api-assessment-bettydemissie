package com.cbfacademy.apiassessment.model.dto;

import com.cbfacademy.apiassessment.model.enums.Status;

public class IssueDTO {

        private long id;
        private String title;
        private String description;
        private Status status;
        private EmployeeDTO assignedTo;
        private EmployeeDTO createdBy;

        public IssueDTO() {

        }

        public IssueDTO(long id, String title, String description, Status status,
                     EmployeeDTO assignedTo, EmployeeDTO createdBy) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.status = status;
                this.assignedTo = assignedTo;
                this.createdBy = createdBy;
        }

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

        public EmployeeDTO getAssignedTo() {
                return assignedTo;
        }

        public void setAssignedTo(EmployeeDTO assignedTo) {
                this.assignedTo = assignedTo;
        }

        public EmployeeDTO getCreatedBy() {
                return createdBy;
        }

        public void setCreatedBy(EmployeeDTO createdBy) {
                this.createdBy = createdBy;
        }

        @Override
        public String toString() {
                return "IssueDTO{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", status='" + status + '\'' +
                        ", assignedTo=" + assignedTo +
                        ", createdBy=" + createdBy +
                        '}';
        }
}


