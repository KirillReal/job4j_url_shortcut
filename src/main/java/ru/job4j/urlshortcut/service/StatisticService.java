package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.StatisticRepository;

import javax.transaction.Transactional;

@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Transactional
    public void updateUrlStatistic(Url url) {
        this.statisticRepository.callProcedureIncrementColumn(url.getId());
    }
}

