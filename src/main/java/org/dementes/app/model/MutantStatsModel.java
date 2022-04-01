package org.dementes.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "mutantstats")
public class MutantStatsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mutantstats_id_seq")
    @SequenceGenerator(name = "mutantstats_id_seq", sequenceName = "mutantstats_id_seq",allocationSize=1)
    @Column(name = "id", updatable=false)
    private Long id;

    @Column(name = "mutante", nullable = true)
    private int countMutantDna;

    @Column(name = "humano", nullable = true)
    private int countHumanDna;

    public MutantStatsModel() {
    }

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
