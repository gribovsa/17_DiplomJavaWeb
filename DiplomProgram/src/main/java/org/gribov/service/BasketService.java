package org.gribov.service;

import org.gribov.api.OrderRequest;
import org.gribov.model.Basket;
import org.gribov.model.Hydrobiont;
import org.gribov.model.Order;
import org.gribov.model.User;
import org.gribov.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс - сервис, вся логика работы с корзинами пользователей
 * (сгруппированными по basket_num)
 * в одной большой корзине.
 */
@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;

    /**
     * Метод возвращает список записей (покупок) по номеру корзины basket_num
     */
    public List<Basket> getBasketByBasketNum(Long basketNum) {
        return basketRepository.findByBasketNum(basketNum);
    }

    /**
     * Метод возвращает список записей (покупок) по id пользователя
     */
    public List<Basket> getBasketByUserId(Long userId) {
        return basketRepository.findByUserId(userId);
    }

    /**
     * Метод возвращает количество купленных гидробионтов по id гидробионта
     * это необходимо для статистики - будем заполнять поле рейтинг у гидробионта
     */
    public int getQuantityBasketByHydrobiontId(Long hydrobiontId) {
        return basketRepository.findByHydrobiontId(hydrobiontId).size();
    }

    /**
     * Метод создания козины покупок
     */
    public Basket createBasket(User user, Hydrobiont hydrobiont){
        Basket basket = new Basket(hydrobiont.getId(), user.getId());
        return basket;
    }


}
