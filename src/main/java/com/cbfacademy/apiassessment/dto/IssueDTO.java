package com.cbfacademy.apiassessment.dto;

public class IssueDTO {

        private long id;
        private String title;
        private String description;
        private String status;
        private EmployeeDTO assignedTo;
        private EmployeeDTO createdBy;

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


