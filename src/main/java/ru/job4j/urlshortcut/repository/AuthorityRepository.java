package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority getAuthoritiesByAuthority(String authority);
}
