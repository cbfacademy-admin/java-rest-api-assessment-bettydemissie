package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.model.entities.Issue;
import com.cbfacademy.apiassessment.model.enums.Status;
import com.cbfacademy.apiassessment.repository.IssueRepository;
import com.cbfacademy.apiassessment.service.IssueService;
import com.cbfacademy.apiassessment.exemptionhandling.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IssueServiceTests {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private IssueService issueService;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllIssues() {
        // Mock data
        List<Issue> mockIssues = new ArrayList<>();
        when(issueRepository.getAllIssues()).thenReturn(mockIssues);
        // Test
        List<Issue> result = issueService.getAllIssues();
        // Assertions
        assertNotNull(result);
        assertEquals(mockIssues, result);
        verify(issueRepository, times(1)).getAllIssues();
    }

    @Test
    void addIssue() {
        // Mock data
        Issue mockIssue = new Issue();
        doNothing().when(issueRepository).addIssue(mockIssue);
        // Test
        assertDoesNotThrow(() -> issueService.addIssue(mockIssue));
        // Verification
        verify(issueRepository, times(1)).addIssue(mockIssue);
    }


    @Test
    void fetchIssueDetails_issueFound() {
        // Mock data
        Long issueId = 1L;
        Issue mockIssue = new Issue();
        when(issueRepository.fetchIssueDetails(issueId)).thenReturn(mockIssue);
        // Test
        Issue result = issueService.fetchIssueDetails(issueId);
        // Assertions
        assertNotNull(result);
        assertEquals(mockIssue, result);
        verify(issueRepository, times(1)).fetchIssueDetails(issueId);
    }

    @Test
    void fetchIssueDetails_issueNotFound() {
        // Mock data
        Long issueId = 1L;
        when(issueRepository.fetchIssueDetails(issueId)).thenThrow(new NotFoundException("Issue not found"));
        // Test and Assertions
        NotFoundException exception = assertThrows(NotFoundException.class, () -> issueService.fetchIssueDetails(issueId));
        assertNotNull(exception);
        verify(issueRepository, times(1)).fetchIssueDetails(issueId);
    }

    @Test
    void updateIssueByStatus() {
        // Mock data
        Long issueId = 1L;
        String status = "IN_PROGRESS";
        doNothing().when(issueRepository).updateIssueByStatus(issueId, status);
        // Test
        assertDoesNotThrow(() -> issueService.updateIssueByStatus(issueId, status));
        // Verification
        verify(issueRepository, times(1)).updateIssueByStatus(issueId, status);
    }

    @Test
    void deleteIssue() {
        // Mock data
        Long issueId = 1L;
        doNothing().when(issueRepository).deleteIssue(issueId);
        // Test
        assertDoesNotThrow(() -> issueService.deleteIssue(issueId));
        // Verification
        verify(issueRepository, times(1)).deleteIssue(issueId);
    }

    @Test
    void getIssuesByStatus() {
        // Mock data
        Status status = Status.IN_PROGRESS;
        List<Issue> mockIssues = new ArrayList<>();
        when(issueRepository.getIssuesByStatus(status)).thenReturn(mockIssues);
        // Test
        List<Issue> result = issueService.getIssuesByStatus(status);
        // Assertions
        assertNotNull(result);
        assertEquals(mockIssues, result);
        verify(issueRepository, times(1)).getIssuesByStatus(status);
    }

    @Test
    void getIssuesAssignedToEmployee() {
        // Mock data
        Long employeeId = 100L;
        List<Issue> mockIssues = new ArrayList<>();
        when(issueRepository.getIssuesAssignedToEmployee(employeeId)).thenReturn(mockIssues);
        // Test
        List<Issue> result = issueService.getIssuesAssignedToEmployee(employeeId);
        // Assertions
        assertNotNull(result);
        assertEquals(mockIssues, result);
        verify(issueRepository, times(1)).getIssuesAssignedToEmployee(employeeId);
    }
}
