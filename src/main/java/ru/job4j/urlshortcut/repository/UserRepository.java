package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByUsername(String username);

    boolean existsUserByUsername(String username);
}
