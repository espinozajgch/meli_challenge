package org.dementes.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MutantStatsModelTest {

    private MutantStatsModel mutantStatsModel;

    @BeforeEach
    void setUp() {
        mutantStatsModel = new MutantStatsModel();
        mutantStatsModel.setId(1L);
        mutantStatsModel.setCountMutantDna(40);
        mutantStatsModel.setCountHumanDna(100);
    }

    @Test
    void getId() {
        assertEquals(1, mutantStatsModel.getId());
    }

    @Test
    void getCountMutantDna() {
        assertEquals(40, mutantStatsModel.getCountMutantDna());
    }

    @Test
    void getCountHumanDna() {
        assertEquals(100, mutantStatsModel.getCountHumanDna());
    }

}