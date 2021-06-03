package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Long> {
    Optional<Url> getUrlByUrl(String url);

    Optional<Url> getUrlByCode(String code);
}
