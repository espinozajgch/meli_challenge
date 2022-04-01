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
                }
                if (countMutantSequence >= maxMutantSequence)
                    break;
            }/**/

		LOGGER.debug("Fin del recorrido");
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

    /**
     *
     * @param dna : array que contiene los nucleotidos del adn
     * @param i : posicion del array
     * @param j : posicion de la base nitrogenada dentro del string de nuecletidos
     * @param base_nitrogenada : string que contiene la representacion del compuesto organico
     * @param sequenceCount : int que representa el conteo del tamaño de la secuencia encontrada
     * @param maxSequenceCount : repesenta el tamaño maximo de la secuencia para determinar si es mutante
     * @param posicion : int que representa la posicion a buscar (horizontal, vertical, diagonal)
     * @return
     */

    @Override
    public boolean getBaseNitrogenada(String[] dna,int i, int j,String base_nitrogenada, int sequenceCount, int maxSequenceCount, int posicion){

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

    public void printArray(String dna[]){
        Arrays.stream(dna).forEach(l -> System.out.println("["+l+"]"));
        System.out.println("-----------------------------------------");
    }
}
