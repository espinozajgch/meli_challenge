package org.dementes.app.pojo;

import java.util.List;

public class DnaWrapper {
    List<String> dna;

    public List<String> getDna(){
        return dna;
    }

    public String[] listToArrays(){
        String[] stringArray = dna.toArray(String[]::new);
        return stringArray;
    }
}
