package org.gribov.service;

import lombok.Data;
import org.gribov.model.Buy;
import org.gribov.repository.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс - сервис, вся логика работы с покупками пользователей
 * (сгруппированными по basket_num)
 * в одной большой корзине.
 */
@Data
@Service
public class BuyService {
    public static long sequence = 1L;
    private Buy buy; //создадим покупку, доступную всему классу - сервису

    @Autowired
    BuyRepository buyRepository;

    /**
     * Метод создания покупки
     */
    public Buy createBuy(Long hydrobiontId) {
        buy = new Buy(hydrobiontId);
        buyRepository.save(buy);
        return buy;
    }

    /**
     * Метод генерации нового номера корзины
     */
    public Long setIncrementBasketNum() {
        this.buy.setBasketNum(sequence++);
        return buy.getBasketNum();
    }


    /**
     * Метод возвращает список записей (покупок) по номеру корзины basket_num
     */
    public List<Buy> getBuyByBasketNum(Long basketNum) {
        return buyRepository.findByBasketNum(basketNum);
    }


    /**
     * Метод возвращает количество купленных гидробионтов по id гидробионта
     * это необходимо для статистики - будем заполнять поле рейтинг у гидробионта
     */
    public int getQuantityBuyByHydrobiontId(Long hydrobiontId) {
        return buyRepository.findByHydrobiontId(hydrobiontId).size();
    }


}
