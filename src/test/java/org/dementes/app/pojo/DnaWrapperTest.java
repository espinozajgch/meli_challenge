package org.dementes.app.pojo;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DnaWrapperTest {

    DnaWrapper dnaWrapper;

    private String[] dna;

    @BeforeEach
    public void setUp(){
        dnaWrapper = new DnaWrapper();
        dna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    }

    @Test
    void getDna() {
        assertNull(dnaWrapper.getDna(), "should be null");
        dnaWrapper.setDna(Arrays.asList(dna));
        assertNotNull(dnaWrapper.getDna(), "should not be null");
    }

    @Test
    void listToArrays() {
        dnaWrapper.setDna(Arrays.asList(dna));
        assertNotNull(dnaWrapper.listToArrays(), "should not be null");
        assertArrayEquals(dna,dnaWrapper.listToArrays());

    }/**/
}