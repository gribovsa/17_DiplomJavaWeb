package org.gribov.repository;

import org.gribov.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Репозиторий покупок
 */
@Repository
public interface BuyRepository extends JpaRepository<Buy, Long> {

    List<Buy> findByBasketNum (Long basketNum);
    List<Buy> findByHydrobiontId (Long hydrobiontId);

}
