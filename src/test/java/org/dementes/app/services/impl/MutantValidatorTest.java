package org.dementes.app.services.impl;

import org.dementes.app.model.MutantStatsModel;
import org.dementes.app.services.MutantStatsService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantValidatorTest {

    private String[] dna;

    @InjectMocks
    private MutantValidator mutantValidator;

    @Mock
    private MutantStatsService mutantStatsService;

    @Mock
    private MutantStatsModel mutantStatsModel;

    @Before
    public void setUp() {
        }

    @Test
    public void validator() {
        dna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantValidator.validator(dna));

        dna = new String[]{"TTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(mutantValidator.validator(dna));

        dna = new String[]{};
        assertFalse(mutantValidator.validator(dna));
    }

}