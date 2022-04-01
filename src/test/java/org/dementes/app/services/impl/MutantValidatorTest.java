package org.dementes.app.services.impl;

import org.dementes.app.model.MutantStatsModel;
import org.dementes.app.services.MutantStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MutantValidatorTest {

    private String[] dna;

    @InjectMocks
    MutantValidator mutantValidator;

    @Mock
    MutantStatsService mutantStatsService;

    @Mock
    MutantStatsModel mutantStatsModel;

    @BeforeEach
    void setUp() {
        }

    @Test
    void validator() {
        dna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantValidator.validator(dna));

        dna = new String[]{"TTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantValidator.validator(dna));

        dna = new String[]{};
        assertFalse(mutantValidator.validator(dna));
    }

}