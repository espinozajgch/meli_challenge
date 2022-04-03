package org.dementes.app.pojo;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DnaWrapperTest {

    private DnaWrapper dnaWrapper;

    private String[] dna;

    @Before
    public void setUp(){
        dnaWrapper = new DnaWrapper();
        dna = new String[]{"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    }

    @Test
    public void getDna() {
        assertNull(dnaWrapper.getDna(), "should be null");
        dnaWrapper.setDna(Arrays.asList(dna));
        assertNotNull(dnaWrapper.getDna(), "should not be null");
    }

    @Test
    public void listToArrays() {
        dnaWrapper.setDna(Arrays.asList(dna));
        assertNotNull(dnaWrapper.listToArrays(), "should not be null");
        assertArrayEquals(dna,dnaWrapper.listToArrays());

    }/**/
}