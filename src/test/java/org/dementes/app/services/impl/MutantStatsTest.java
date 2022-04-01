package org.dementes.app.services.impl;

import org.dementes.app.model.MutantStatsModel;
import org.dementes.app.repository.MutantStatsRepository;
import org.dementes.app.services.MutantStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class MutantStatsTest {

    @InjectMocks
    MutantStats mutantStats;

    @Mock
    MutantStatsModel mutantStatsModel;

    @Mock
    MutantStatsRepository mutantStatsRepository;

    @BeforeEach
    void setUp() {
        when(mutantStatsModel.getCountHumanDna()).thenReturn(1);
        when(mutantStatsModel.getCountMutantDna()).thenReturn(10);
        when(mutantStatsRepository.findAllCount()).thenReturn(Optional.empty());
        when(mutantStatsRepository.findAllCount()).thenReturn(Optional.of(mutantStatsModel));
    }

    @Test
    void findAllCount() {
        MutantStatsModel mutantStatsAll = mutantStats.findAllCount();
        assertEquals(1,mutantStatsAll.getCountHumanDna());
        assertEquals(10,mutantStatsAll.getCountMutantDna());
        assertEquals(mutantStats.findAllCount(), mutantStatsModel);
    }

    @Test
    void save() {
        when(mutantStats.save(mutantStatsModel)).thenReturn(mutantStatsModel);
        assertEquals(mutantStats.save(mutantStatsModel), mutantStatsModel);
    }
}