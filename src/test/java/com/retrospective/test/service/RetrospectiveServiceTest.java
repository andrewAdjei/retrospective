package com.retrospective.test.service;

import com.retrospective.test.entity.Retrospective;
import com.retrospective.test.repository.RetrospectiveRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RetrospectiveServiceTest {

    @Mock
    private RetrospectiveRepository retrospectiveRepository;

    @InjectMocks
    private RetroServiceImpl retrospectiveService;

    @Test
    public void whenFindByName_thenReturnRetrospective() {

        Retrospective retrospective = new Retrospective();
        retrospective.setName("testRetrospective");
        retrospective.setSummary("Test summary");
        retrospective.setDate("2024-02-10");
        retrospective.setParticipants(Arrays.asList("John", "Jane"));

        when(retrospectiveRepository.findByName("testRetrospective")).thenReturn(retrospective);
        Retrospective foundRetrospective = retrospectiveService.findByName("testRetrospective");
        assertThat(foundRetrospective.getName()).isEqualTo(retrospective.getName());
    }
}
