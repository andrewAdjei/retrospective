package com.retrospective.test.repository;


import com.retrospective.test.entity.Retrospective;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RetrospectiveRepositoryTest {

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @Test
    public void whenFindByName_thenReturnRetrospective() {

        Retrospective retrospective = new Retrospective();
        retrospective.setName("testRetrospective");
        retrospective.setSummary("Test Summary");
        retrospective.setDate("2024-02-13");
        retrospective.setParticipants(Arrays.asList("John", "Ama"));

        retrospectiveRepository.save(retrospective);

        Retrospective foundRetrospective = retrospectiveRepository.findByName("testRetrospective");
        assertThat(foundRetrospective.getName()).isEqualTo(retrospective.getName());
      }

}
