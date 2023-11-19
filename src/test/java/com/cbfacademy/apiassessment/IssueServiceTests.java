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
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllIssues() {
        List<Issue> mockIssues = new ArrayList<>();
        when(issueRepository.getAllIssues()).thenReturn(mockIssues);

        List<Issue> result = issueService.getAllIssues();

        assertNotNull(result);
        assertEquals(mockIssues, result);
        verify(issueRepository, times(1)).getAllIssues();
    }

    @Test
    void addIssue() {
        Issue mockIssue = new Issue();
        doNothing().when(issueRepository).addIssue(mockIssue);

        assertDoesNotThrow(() -> issueService.addIssue(mockIssue));

        verify(issueRepository, times(1)).addIssue(mockIssue);
    }

    @Test
    void fetchIssueDetails_issueFound() {
        Long issueId = 1L;
        Issue mockIssue = new Issue();
        when(issueRepository.fetchIssueDetails(issueId)).thenReturn(mockIssue);

        Issue result = issueService.fetchIssueDetails(issueId);

        assertNotNull(result);
        assertEquals(mockIssue, result);
        verify(issueRepository, times(1)).fetchIssueDetails(issueId);
    }

    @Test
    void fetchIssueDetails_issueNotFound() {
        Long issueId = 1L;
        when(issueRepository.fetchIssueDetails(issueId)).thenThrow(new NotFoundException("Issue not found"));

        NotFoundException exception = assertThrows(NotFoundException.class, () -> issueService.fetchIssueDetails(issueId));

        assertNotNull(exception);
        verify(issueRepository, times(1)).fetchIssueDetails(issueId);
    }

    @Test
    void updateIssueByStatus() {
        Long issueId = 1L;
        String status = "IN_PROGRESS";
        doNothing().when(issueRepository).updateIssueByStatus(issueId, status);

        assertDoesNotThrow(() -> issueService.updateIssueByStatus(issueId, status));

        verify(issueRepository, times(1)).updateIssueByStatus(issueId, status);
    }

    @Test
    void deleteIssue() {
        Long issueId = 1L;
        doNothing().when(issueRepository).deleteIssue(issueId);

        assertDoesNotThrow(() -> issueService.deleteIssue(issueId));

        verify(issueRepository, times(1)).deleteIssue(issueId);
    }

    @Test
    void getIssuesByStatus() {
        Status status = Status.IN_PROGRESS;
        List<Issue> mockIssues = new ArrayList<>();
        when(issueRepository.getIssuesByStatus(status)).thenReturn(mockIssues);

        List<Issue> result = issueService.getIssuesByStatus(status);

        assertNotNull(result);
        assertEquals(mockIssues, result);
        verify(issueRepository, times(1)).getIssuesByStatus(status);
    }

    @Test
    void getIssuesAssignedToEmployee() {
        Long employeeId = 100L;
        List<Issue> mockIssues = new ArrayList<>();
        when(issueRepository.getIssuesAssignedToEmployee(employeeId)).thenReturn(mockIssues);

        List<Issue> result = issueService.getIssuesAssignedToEmployee(employeeId);

        assertNotNull(result);
        assertEquals(mockIssues, result);
        verify(issueRepository, times(1)).getIssuesAssignedToEmployee(employeeId);
    }
}
