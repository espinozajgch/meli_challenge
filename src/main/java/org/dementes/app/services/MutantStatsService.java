package org.dementes.app.services;

import org.dementes.app.model.MutantStatsModel;

public interface MutantStatsService {

    MutantStatsModel findAllCount();

    MutantStatsModel save(MutantStatsModel mutantStatsModel);
}
