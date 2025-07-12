package org.gribov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gribov.model.Order;
import org.gribov.repository.BuyRepository;
import org.gribov.repository.OrderRepository;
import org.gribov.repository.UserRepository;
import org.gribov.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

  // Spring это все заинжектит
  private final BuyRepository buyRepository;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;
  @Autowired
  private final CustomUserDetailsService customUserDetailsService;
  @Autowired
  private BuyService buyService;


  /**
   * Метод создания заказа для текущего пользователя
   */
  public Order createOrder() {
    // Проверим, есть номер корзины (созданы ли покупки, сгруппированные в корзину)
    if (buyRepository.findByBasketNum(customUserDetailsService.getNowBasketNum()).isEmpty()) {
      throw new NoSuchElementException("BASKET WITH NUMBER NOT FOUND \"" + customUserDetailsService.getNowBasketNum() + "\"");
    }
    // Существует пользователь с таким Id
    if (userRepository.findById(customUserDetailsService.getNowUserId()).isEmpty()) {
      throw new NoSuchElementException("USER WITH ID NOT FOUND \"" + customUserDetailsService.getNowUserId() + "\"");
    }
    log.info("CREATE AN ORDER USERID{} AND BASKETNUM{} ALLOWED", customUserDetailsService.getNowUserId(), customUserDetailsService.getNowBasketNum());
    //Если всё ок, то создаём экземпляр класса Order (предаём basketNum созданной корзины и userId)

    Order order = new Order(null, customUserDetailsService.getNowBasketNum(), customUserDetailsService.getNowUserId(), buyService.getTotalPrice(customUserDetailsService.getNowBasketNum()), LocalDateTime.now(),null);
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
