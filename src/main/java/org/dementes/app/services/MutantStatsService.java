package org.dementes.app.services;

import org.dementes.app.model.MutantStatsModel;

import java.util.List;

public interface MutantStatsService {

    List<MutantStatsModel> getMutantStats();

    MutantStatsModel getMutantStatsById();

    MutantStatsModel findAllCount();

    MutantStatsModel save(MutantStatsModel mutantStatsModel);
}
