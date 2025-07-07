package org.gribov.service;

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
@Service
public class BuyService {
    @Autowired
    BuyRepository buyRepository;

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

    /**
     * Метод создания покупок
     */
    public Buy createBuy(Long hydrobiontId){
        Buy buy = new Buy(hydrobiontId);
        buyRepository.save(buy);
        return buy;
    }

}
