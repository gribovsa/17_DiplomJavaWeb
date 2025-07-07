package org.gribov.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс в котором формируется необходимая информация для создания заказа
 */
@Data
@AllArgsConstructor
public class OrderRequest {

  /**
   * Идентификатор пользователя
   */
  private long userId;


  /**
   * Номер корзины
   */
  private long basketNum;

}
