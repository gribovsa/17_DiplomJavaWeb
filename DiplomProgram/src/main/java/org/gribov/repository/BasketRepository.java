package org.gribov.repository;

import org.gribov.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * Репозиторий корзин
 */
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findByBasketNum (Long basketNum);
    List<Basket> findByUserId (Long userId);
    List<Basket> findByHydrobiontId (Long hydrobiontId);
}
