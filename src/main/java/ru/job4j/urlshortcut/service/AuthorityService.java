package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Authority;
import ru.job4j.urlshortcut.repository.AuthorityRepository;

@Service
public class AuthorityService {
    private final AuthorityRepository authoritiesRepository;

    public AuthorityService(AuthorityRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    public Authority getAuthorityByAuthority(String name) {
        return this.authoritiesRepository.getAuthoritiesByAuthority(name);
    }
}
