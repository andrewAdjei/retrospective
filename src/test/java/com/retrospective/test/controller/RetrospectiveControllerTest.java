package com.retrospective.test.controller;

import com.retrospective.test.conroller.RetrospectiveController;
import com.retrospective.test.entity.Retrospective;
import com.retrospective.test.service.RetroServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class RetrospectiveControllerTest {

    @Mock
    private RetroServiceImpl retrospectiveService;

    @InjectMocks
    private RetrospectiveController retrospectiveController;

    @Test
    public void whenGetRetrospectiveByName_thenReturnRetrospective() {
        // Arrange
        Retrospective retrospective = new Retrospective();
        retrospective.setName("testRetrospective");
        retrospective.setSummary("Test summary");
        retrospective.setDate("2024-02-10");
        retrospective.setParticipants(Arrays.asList("John", "Jane"));

        when(retrospectiveService.findByName("testRetrospective")).thenReturn(retrospective);

        // Act
        ResponseEntity<Retrospective> response = retrospectiveController.getRetrospectiveByName("testRetrospective");
        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(retrospective.getName());
    }
}
