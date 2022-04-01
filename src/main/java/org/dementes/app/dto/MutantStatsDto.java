package org.dementes.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.dementes.app.model.MutantStatsModel;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MutantStatsDto {

    @JsonProperty("count_mutant_dna")
    private int countMutantDna;

    @JsonProperty("count_human_dna")
    private int countHumanDna;

    @JsonProperty("ratio")
    private double ratio;

    public MutantStatsDto(MutantStatsModel mutantStatsModel) {
        this.countMutantDna = mutantStatsModel.getCountMutantDna();
        this.countHumanDna = mutantStatsModel.getCountHumanDna();
    }

    public int getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(int countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public int getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(int countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public double getRatio() {
        if(this.countHumanDna>0)
            return this.ratio = (double) this.countMutantDna / this.countHumanDna;
        else
            return 0;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
