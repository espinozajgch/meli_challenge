package org.dementes.app.services;

public interface MutantValidatorService {

    boolean validator(String dna[]);
    boolean getBaseNitrogenada(String[] dna,int i, int j,String base_nitrogenada, int sequenceCount, int maxSequenceCount, int posicion);

    }
