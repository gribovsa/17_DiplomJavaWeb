package org.gribov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gribov.api.OrderRequest;
import org.gribov.model.Order;
import org.gribov.repository.BuyRepository;
import org.gribov.repository.OrderRepository;
import org.gribov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

  // Spring это все заинжектит
  private final BuyRepository buyRepository;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  //Читаем максимально возможное количество книг у читателя на руках
  //из файла Application.yml, если там запись отсутствует, то по умолчанию будет 1шт
  @Value("${application.max-allowed-books:1}")
  private int maxAllowedBooks;


  /**
   * Метод регистрации заказа
   */
  public Order createOrder(OrderRequest request) {
    if (buyRepository.findByBasketNum(request.getBasketNum()).isEmpty()) {
      throw new NoSuchElementException("Не найдена корзина с номером \"" + request.getBasketNum() + "\"");
    }
    if (userRepository.findById(request.getUserId()).isEmpty()) {
      throw new NoSuchElementException("Не найден пользователь с идентификатором \"" + request.getUserId() + "\"");
    }

    log.info("Создать заказ разрешается{}", request);
    //Если всё ок, то создаём экземпляр класса Order
    Order order = new Order(request.getBasketNum(), request.getUserId());
    log.info(order.toString());
    orderRepository.save(order);


    return order;
  }

  /**
   * Метод возвращает список всех заказов
   */
  public List<Order> getAllOrder() {
    return orderRepository.findAll();
  }

  /**
   * Метод ищет заказ в репозитории по id
   */
  public Order getInfoById(Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  /**
   * Метод ищет заказ в репозитории по id пользователя
   */
  public List<Order> getOrderByIdUser(Long id) {
    return orderRepository.findByUserId(id);
  }

  /**
   * Метод закрывает заказ
   */
  public Order setReturnedOrder(Long id) {
    Optional<Order> returnedOrder = orderRepository.findById(id);
    returnedOrder.get().setReturnedTimestamp(LocalDateTime.now());
    return orderRepository.save(returnedOrder.get());
  }

}
