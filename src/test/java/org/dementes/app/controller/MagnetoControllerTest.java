package org.dementes.app.controller;

import org.dementes.app.dto.MutantStatsDto;
import org.dementes.app.model.MutantStatsModel;
import org.dementes.app.pojo.DnaWrapper;
import org.dementes.app.services.MutantStatsService;
import org.dementes.app.services.MutantValidatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MagnetoControllerTest {

    @InjectMocks
    private MagnetoController magnetoController;

    @Mock
    private MutantStatsService mutantStatsService;

    @Mock
    private MutantValidatorService mutantValidatorService;

    @Mock
    private MutantStatsModel mutantStatsModel;

    private ResponseEntity<?> responseEntity;

    private MutantStatsDto mutantStats;

    private String[] dna;

    private DnaWrapper dnaWrapper;

    @Before
    public void setUp() {
        when(mutantStatsModel.getCountMutantDna()).thenReturn(40);
        when(mutantStatsModel.getCountHumanDna()).thenReturn(100);
        when(mutantStatsService.findAllCount()).thenReturn(mutantStatsModel);
        when(mutantStatsService.findAllCount()).thenReturn(mutantStatsModel);

        mutantStats = new MutantStatsDto(mutantStatsService.findAllCount());
    }

    @Test
    public void dnaRecognition() {

        dnaWrapper = new DnaWrapper();

        dna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        when(mutantValidatorService.validator(dna)).thenReturn(true);
        assertTrue(mutantValidatorService.validator(dna));
        dnaWrapper.setDna(Arrays.asList(dna));
        assertEquals(magnetoController.dnaRecognition(dnaWrapper).getStatusCode(), HttpStatus.OK);

        dna = new String[]{};
        dnaWrapper.setDna(Arrays.asList(dna));
        when(mutantValidatorService.validator(dna)).thenReturn(false);
        assertFalse(mutantValidatorService.validator(dna));
        assertEquals(magnetoController.dnaRecognition(dnaWrapper).getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    public void dnaStats() {
        assertEquals(100,mutantStats.getCountHumanDna());
        assertEquals(40,mutantStats.getCountMutantDna());
        Assertions.assertEquals(0.4,mutantStats.getRatio());
        assertEquals(mutantStatsService.findAllCount(), mutantStatsModel);

        responseEntity = new ResponseEntity<>(mutantStats, HttpStatus.OK);
        assertEquals(magnetoController.dnaStats().getStatusCode(), responseEntity.getStatusCode());
        assertNotNull(magnetoController.dnaStats(),"shold not be null");
    }
}