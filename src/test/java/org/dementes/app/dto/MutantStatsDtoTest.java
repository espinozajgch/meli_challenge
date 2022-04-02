package org.dementes.app.dto;

import org.dementes.app.model.MutantStatsModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantStatsDtoTest {

    private MutantStatsDto mutantStatsDto;

    private MutantStatsModel mutantStatsModel;

    @Before
    public void setUp() {
        mutantStatsModel = new MutantStatsModel();
        mutantStatsModel.setId(1L);
        mutantStatsModel.setCountMutantDna(40);
        mutantStatsModel.setCountHumanDna(100);
        mutantStatsDto = new MutantStatsDto(mutantStatsModel);
    }

    @Test
    public void getCountMutantDna() {
        assertEquals(40,mutantStatsDto.getCountMutantDna());
    }

    @Test
    public void setCountHumanDna() {
        assertEquals(100,mutantStatsDto.getCountHumanDna());
    }

    @Test
    public void getRatio() {
        assertEquals(0.4,mutantStatsDto.getRatio(),"should be 0.4");
        mutantStatsDto.setCountMutantDna(50);
        assertNotEquals(0.4,mutantStatsDto.getRatio(),"should be not equal to 0.4");
        mutantStatsDto.setCountHumanDna(0);
        assertEquals(0,mutantStatsDto.getRatio(), "should be 0");

    }

}