package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Statistic;

public interface StatisticRepository extends CrudRepository<Statistic, Long> {

    @Query(value = "select updateStatistic(:urlId);", nativeQuery = true)
    void callProcedureIncrementColumn(Long urlId);
}
