package org.gribov.repository;

import org.gribov.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Репозиторий заказов
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Метод - название метода написано в соответствии с конвенцией.
     * JPA Repository methods convention — это соглашение об именовании методов репозиториев
     * в Spring Data JPA, которое позволяет определять методы запросов без написания
     * явных запросов SQL или JPQL.
     * Метод использую для поиска заказов по id пользователя и времени закрытия заказа равного null (т.е заказ в работе)
     * @param userId - поле есть у сущности Order
     * @param returnedTimestamp - поле есть у сущности Order
     * @return возвращает искомых заказов
     */
    List<Order> findByUserIdAndReturnedTimestamp(Long userId, LocalDateTime returnedTimestamp);

    /**
     * Аналогичный метод, только возвращает все заказы по id пользователя
     * @param userId идентификатор пользователя (поле у сущности Order)
     * @return список искомых заказов
     */
    List<Order> findByUserId(Long userId);

}
