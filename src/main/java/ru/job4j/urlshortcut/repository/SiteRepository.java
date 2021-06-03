package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.User;

public interface SiteRepository extends CrudRepository<Site, Long> {
    boolean existsSiteByName(String name);

    Site getSiteByUser(User user);
}
