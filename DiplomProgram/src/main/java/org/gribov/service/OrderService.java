package org.gribov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gribov.api.OrderRequest;
import org.gribov.model.Buy;
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

  public static long sequence = 1L;
  //private Buy buy;

  // Spring это все заинжектит
  private final BuyRepository buyRepository;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;
  private final BuyService buyService;


  /**
   * Метод создания заказа
   */
  public Order createOrder(Long userId) {
    // Проверим, есть номер корзины (созданы ли покупки, сгруппированные в корзину)
    if (buyRepository.findByBasketNum(buyService.getBuy().getBasketNum()).isEmpty()) {
      throw new NoSuchElementException("Не найдена корзина с номером \"" + buyService.getBuy().getBasketNum() + "\"");
    }
    // Существует пользователь с таким Id
    if (userRepository.findById(userId).isEmpty()) {
      throw new NoSuchElementException("Не найден пользователь с идентификатором \"" + userId + "\"");
    }
    log.info("Создать заказ для userId{} c basketNum{} разрешается", userId, buyService.getBuy().getBasketNum());
    //Если всё ок, то создаём экземпляр класса Order (предаём basketNum созданной корзины и userId)
    Order order = new Order(buyService.getBuy().getBasketNum(), userId);
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
