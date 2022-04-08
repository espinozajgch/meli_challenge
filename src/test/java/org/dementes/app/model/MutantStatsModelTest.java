package org.dementes.app.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantStatsModelTest {

    private MutantStatsModel mutantStatsModel;

    @Before
    public void setUp() {
        mutantStatsModel = new MutantStatsModel();
        mutantStatsModel.setId(1L);
        mutantStatsModel.setCountMutantDna(40);
        mutantStatsModel.setCountHumanDna(100);
    }

    @Test
    public void getId() {
        assertEquals(1, mutantStatsModel.getId());
    }

    @Test
    public void getCountMutantDna() {
        assertEquals(40, mutantStatsModel.getCountMutantDna());
    }

    @Test
    public void getCountHumanDna() {
        assertEquals(100, mutantStatsModel.getCountHumanDna());
    }

}