package org.gribov.repository;


import org.gribov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий пользователей
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

