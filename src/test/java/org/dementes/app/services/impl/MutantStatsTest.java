package org.dementes.app.services.impl;

import org.dementes.app.model.MutantStatsModel;
import org.dementes.app.repository.MutantStatsRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantStatsTest {

    @InjectMocks
    private MutantStats mutantStats;

    @Mock
    private MutantStatsModel mutantStatsModel;

    @Mock
    private MutantStatsRepository mutantStatsRepository;

    @Before
    public void setUp() {
        when(mutantStatsModel.getCountHumanDna()).thenReturn(1);
        when(mutantStatsModel.getCountMutantDna()).thenReturn(10);
        when(mutantStatsRepository.findAllCount()).thenReturn(Optional.empty());
        when(mutantStatsRepository.findAllCount()).thenReturn(Optional.of(mutantStatsModel));
    }

    @Test
    public void findAllCount() {
        MutantStatsModel mutantStatsAll = mutantStats.findAllCount();
        assertEquals(1,mutantStatsAll.getCountHumanDna());
        assertEquals(10,mutantStatsAll.getCountMutantDna());
        assertEquals(mutantStats.findAllCount(), mutantStatsModel);
    }

    @Test
    public void save() {
        when(mutantStats.save(mutantStatsModel)).thenReturn(mutantStatsModel);
        assertEquals(mutantStats.save(mutantStatsModel), mutantStatsModel);
    }
}