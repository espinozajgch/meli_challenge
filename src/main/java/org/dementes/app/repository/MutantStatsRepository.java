package org.dementes.app.repository;

import org.dementes.app.model.MutantStatsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MutantStatsRepository extends JpaRepository<MutantStatsModel, Integer> {

    @Query(value = "select 1 as id, coalesce(sum(mutante),0) mutante,  coalesce(sum(humano),0) humano from mutantstats", nativeQuery = true)
    Optional<MutantStatsModel> findAllCount();


}
