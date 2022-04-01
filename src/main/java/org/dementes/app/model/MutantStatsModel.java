package org.dementes.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "mutantstats")
public class MutantStatsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "mutante", nullable = false)
    private int countMutantDna;

    @Column(name = "humano", nullable = false)
    private int countHumanDna;

    public MutantStatsModel() {
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
