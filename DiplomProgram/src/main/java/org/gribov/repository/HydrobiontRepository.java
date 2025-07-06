package org.gribov.repository;

import org.gribov.model.Hydrobiont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий гидробионтов
 */
@Repository
public interface HydrobiontRepository extends JpaRepository<Hydrobiont, Long> {
}
