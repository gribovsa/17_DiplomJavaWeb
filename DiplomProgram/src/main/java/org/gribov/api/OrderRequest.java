package org.gribov.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Запрос формирование заказа
 */
@Data
@AllArgsConstructor
public class OrderRequest {


  /**
   * Идентификатор пользователя
   */
  private long userId;

  /**
   * Идентификатор гидробионта
   */
  private long basketNum;

}
