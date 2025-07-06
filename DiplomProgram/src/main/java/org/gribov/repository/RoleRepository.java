package org.gribov.repository;

import org.gribov.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий ролей пользователя
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
