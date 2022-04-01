package org.dementes.app.dto;

import org.dementes.app.model.MutantStatsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MutantStatsDtoTest {

    MutantStatsDto mutantStatsDto;

    MutantStatsModel mutantStatsModel;

    @BeforeEach
    void setUp() {
        mutantStatsModel = new MutantStatsModel();
        mutantStatsModel.setId(1L);
        mutantStatsModel.setCountMutantDna(40);
        mutantStatsModel.setCountHumanDna(100);
        mutantStatsDto = new MutantStatsDto(mutantStatsModel);
    }

    @Test
    void getCountMutantDna() {
        assertEquals(40,mutantStatsDto.getCountMutantDna());
    }

    @Test
    void setCountHumanDna() {
        assertEquals(100,mutantStatsDto.getCountHumanDna());
    }

    @Test
    void getRatio() {
        assertEquals(0.4,mutantStatsDto.getRatio(),"should be 0.4");
        mutantStatsDto.setCountMutantDna(50);
        assertNotEquals(0.4,mutantStatsDto.getRatio(),"should be not equal to 0.4");
        mutantStatsDto.setCountHumanDna(0);
        assertEquals(0,mutantStatsDto.getRatio(), "should be 0");


    }

}