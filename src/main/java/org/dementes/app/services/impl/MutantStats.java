package org.dementes.app.services.impl;

import org.dementes.app.model.MutantStatsModel;
import org.dementes.app.repository.MutantStatsRepository;
import org.dementes.app.services.MutantStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantStats implements MutantStatsService {

    @Autowired
    private MutantStatsRepository mutantStatsRepository;

    @Override
    public MutantStatsModel findAllCount() {
        return mutantStatsRepository.findAllCount().orElseThrow(() -> new RuntimeException("No se han recuperado datos estadisticos"));
    }

    public MutantStatsModel save(MutantStatsModel mutantStatsModel) {
        return mutantStatsRepository.save(mutantStatsModel);
    }
}
