package org.dementes.app.services;

public interface MutantValidatorService {

    boolean validator(String dna[]);
    boolean validatorMatriz(String dna[][]);
    boolean routeSecuencePattern(String dna [][], String valor, int i, int j, int posicionMatch, int contadorMatch, int maxMatch ,int mitadArray, int size);
    boolean checkSecuencePattern(String dna [][], String valor ,int i, int j, int iplus, int jplus, int posicionMatch, int contadorMatch, int maxMatch ,int mitadArray, int size, boolean condicion);
}
