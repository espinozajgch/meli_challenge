package org.dementes.app.services.impl;

import org.dementes.app.model.MutantStatsModel;
import org.dementes.app.services.MutantStatsService;
import org.dementes.app.services.MutantValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MutantValidator implements MutantValidatorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MutantValidator.class);

    private String value;
    private int size;
    private int halfArray;
    private int countMutantSequence = 0;
    private int maxSequenceCount = 4;
    private int maxMutantSequence = 2;

    @Autowired
    MutantStatsService mutantStatsService;

    MutantStatsModel mutantStatsModel = new MutantStatsModel();

    /**
     * @param dna :
     * @return boolean
     */
    @Override
    public boolean validator(String[] dna) {
        String[] nucleotido;

        size = dna.length-1;
        halfArray = (dna.length/2);
        countMutantSequence = 0;

        printArray(dna);

        if(size>0)
            for(int i=0;i<=size;i++){

                // Valida si el tamaño de la cadena es igual al tamaño del arreglo
                // y si es mayor o igual al tamaño de la sucuencia a buscar
                if(dna[i].length() == (size+1) && dna[i].length()>=maxSequenceCount){
                    nucleotido  = dna[i].split("");

                    for (int j=0; j<=nucleotido.length-1;j++) {
                        String base_nitrogenada = nucleotido[j];
                        //System.out.print("["+ base_nitrogenada + "]");

                        // Horizontal
                        if(getBaseNitrogenada(dna,i,j,base_nitrogenada, 1, maxSequenceCount, 1)) {
                            countMutantSequence++;
                            j+=maxSequenceCount;

                            LOGGER.info("\n Secuencia horizontal [{}] encontrada, cantidad: {} ", base_nitrogenada, countMutantSequence);
                            if(countMutantSequence>= maxMutantSequence)
                                break;
                        }

                        if(((maxSequenceCount <= halfArray) && (i < size)) || ((maxSequenceCount >= halfArray) && (i < halfArray))) {
                            // Vertical
                            if (getBaseNitrogenada(dna, i, j, base_nitrogenada, 1, maxSequenceCount, 2)) {
                                countMutantSequence++;

                                LOGGER.info("\n Secuencia vertical [{}] encontrada, cantidad: {} ", base_nitrogenada, countMutantSequence);
                                if(countMutantSequence>= maxMutantSequence)
                                    break;
                            }

                            // Diagonal
                            if (getBaseNitrogenada(dna, i, j, base_nitrogenada, 1, maxSequenceCount, 3)) {
                                countMutantSequence++;

                                LOGGER.info("\n Secuencia diagonal [{}] encontrada, cantidad: {} ", base_nitrogenada, countMutantSequence);
                                if (countMutantSequence >= maxMutantSequence)
                                    break;
                            }
                        }
                        /**/
                    }
                    System.out.println("");
                }
                if (countMutantSequence >= maxMutantSequence)
                    break;
            }/**/

		LOGGER.info("Fin del recorrido");
		if(countMutantSequence>=maxMutantSequence) {
            LOGGER.info("Es Muntante por tener {} secuencias iguales", countMutantSequence);
            mutantStatsModel.setCountMutantDna(1);
            mutantStatsModel.setCountHumanDna(0);
            return true;

        }
		else {
            LOGGER.info("No es Muntante, tiene {} secuencias iguales, minimo {}", countMutantSequence, maxMutantSequence);
            mutantStatsModel.setCountMutantDna(0);
            mutantStatsModel.setCountHumanDna(1);
            return false;
        }
    }

    public static boolean getBaseNitrogenada(String[] dna,int i, int j,String base_nitrogenada, int sequenceCount, int maxSequenceCount, int posicion){

        // si lo posicion es vertical, mantego fija la posicion j,
        // en caso contrario (horizontal, diagonal) se aumenta la misma
        int jplus = posicion==2?0:1;

        // si la posicion es horizontal, itero por el string aumentando la posicion j,
        // en caso contrario (vertical, diagonal) se aumenta
        int iplus = posicion==1?0:1;

        boolean arrayCondition = posicion ==1?(i<=dna.length-1):(i<dna.length-1);

        if (arrayCondition){
            String nucleotidos = dna[i+iplus];

            boolean stringCondition = posicion==2?(j<=nucleotidos.length()-1):(j<nucleotidos.length()-1);

            if(stringCondition) {
                if (String.valueOf(nucleotidos.charAt(j+jplus)).equalsIgnoreCase(base_nitrogenada)) {
                    sequenceCount++;

                    if (sequenceCount >= maxSequenceCount) {
                        return true;
                    }

                    if (getBaseNitrogenada(dna, i+iplus, j+jplus,base_nitrogenada, sequenceCount, maxSequenceCount, posicion)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /***
     *
     * @param dna :
     * @return
     */
    public boolean validatorMatriz(String[][] dna) {

        size = dna.length-1;
        halfArray = (dna.length/2);

        printMatriz(dna, 0);
        printMatriz(dna, 1);

        for(int i=0;i<=size;i++){
            for(int j=0;j<=size;j++){

                System.out.println("["+dna[i][j]+"]");
                value = dna[i][j];

                if(!value.equalsIgnoreCase("X")) {
                    if (routeSecuencePattern(dna, value, i, j, 0, 1, countMutantSequence ,halfArray, size)) {
                        countMutantSequence++;
                        LOGGER.info("Secuencia Encontrada: ", value);
                        LOGGER.info("Cantidad de Secuencias: ", countMutantSequence);
                        printMatriz(dna, 1);

                        if(countMutantSequence>=maxMutantSequence)
                            break;
                    }
                    LOGGER.info("Secuencia No Encontrada");
                }
                else{
                    LOGGER.info("Secuencia Omitida");
                }
                System.out.println("");
            }
            System.out.println("");
            if(countMutantSequence>=maxMutantSequence)
                break;

        }
        LOGGER.info("Fin del recorrido");
        printMatriz(dna, 1);
        if(countMutantSequence>=maxMutantSequence) {
            LOGGER.info("Es Muntante por tener {} secuencias iguales", countMutantSequence);
            return true;
        }
        else {
            LOGGER.info("No es Muntante, tiene {} secuencias iguales, minimo {}", countMutantSequence, maxMutantSequence);
            return false;
        }
    }

    /**
     *  @param posicionMatch : representa la ubicacion (horizontal, vertical, diagonal)
     *  del match encontrado: 0: sin match, 1: horizontal, 2: vertical, 3: diagonal, 4: diagonal invertida
     *
     *  @param contadorMatch: lleva el conteo del tamaño de la secuencia encontrada
     *
     *  @param maxMatch: repesenta el tamaño maximo de la secuencia para determinar si es mutante
     *
     *  @return boolean
     *
     */
    @Override
    public boolean routeSecuencePattern(String[][] dna, String valor, int i, int j, int posicionMatch, int contadorMatch, int maxMatch, int mitadArray, int size) {

        /* secuencia horizontal */
        if((posicionMatch == 0 || posicionMatch == 1)){
            if(j<size){
                if(checkSecuencePattern(dna, valor, i, j, i, j+1, 1, contadorMatch, maxMatch , mitadArray, size, (j < mitadArray)))
                    return true;
            }
            contadorMatch = 1;
        }

        /* secuencia vertical */
        if((posicionMatch == 0 || posicionMatch == 2)){
            if(i<size){
                if(checkSecuencePattern(dna, valor, i, j, i+1, j, 2, contadorMatch, maxMatch , mitadArray, size, (i < mitadArray)))
                    return true;
            }
            contadorMatch = 1;
        }

        /* secuencia diagonal */
        if((posicionMatch == 0 || posicionMatch == 3)){
            if((i<size && j<size)){
                if(checkSecuencePattern(dna, valor, i, j, i+1, j+1, 3, contadorMatch, maxMatch , mitadArray, size, (j < mitadArray)))
                    return true;
            }
            contadorMatch = 1;
        }

        /* secuencia diagonal invertida */
        if((posicionMatch == 0 || posicionMatch == 4)){
            if((j<=size && i<size) && (j>0)){
                if(checkSecuencePattern(dna, valor, i, j, i+1, j-1, 4, contadorMatch, maxMatch , mitadArray, size, (j >= mitadArray)))
                    return true;
            }
        }

        return false;
    }

    /***
     * @param iplus : posicion i del nuevo valor a comparar
     * @param jplus : posicion j del nuevo valor a comparar
     * @param condicion : condicion de validacion para determinar si se puede continur con la busqueda de secuencias
     *                  dependiendo de la posicion de busqueda (horizontal, vertical, diagona, diagonal invertido
     * @return boolean
     **/
    @Override
    public boolean checkSecuencePattern(String[][] dna, String valor, int i, int j, int iplus, int jplus, int posicionMatch, int contadorMatch, int maxMatch, int mitadArray, int size, boolean condicion) {
        if(dna[iplus][jplus].equalsIgnoreCase(valor)) {
            contadorMatch++;
            LOGGER.info("Match: {}",contadorMatch);

            if (contadorMatch >= maxMatch) {
                dna[i][j] = "X";
                dna[iplus][jplus] = "X";
                return true;
            }

            if (maxMatch <= (mitadArray) || (condicion)) {
                valor = dna[iplus][jplus];
                if (routeSecuencePattern(dna, valor, iplus, jplus, posicionMatch, contadorMatch, maxMatch, mitadArray, size)) {
                    dna[i][j] = "X";
                    return true;
                }
            }
        }
        return false;
    }

    public void printMatriz(String numeros[][], int tipo){
        for(int i=0;i<=numeros.length-1;i++){
            for(int j=0;j<=numeros.length-1;j++){

                if(tipo==0)
                    System.out.print("["+i+","+j+"]");
                else
                    System.out.print("["+numeros[i][j]+"]");
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------------");
    }

    public void printArray(String dna[]){
        Arrays.stream(dna).forEach(l -> System.out.println("["+l+"]"));
        System.out.println("-----------------------------------------");
    }
}
