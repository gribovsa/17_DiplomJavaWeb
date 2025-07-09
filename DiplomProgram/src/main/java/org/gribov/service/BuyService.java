package org.gribov.service;

import lombok.Data;
import org.gribov.model.Buy;
import org.gribov.repository.BuyRepository;
import org.gribov.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Класс - сервис, вся логика работы с покупками пользователей
 * (сгруппированными по basket_num)
 * в одной большой корзине.
 */
@Data
@Service
public class BuyService {

    @Autowired
    BuyRepository buyRepository;
    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Метод создания покупки
     */
    public Buy createBuy(Long hydrobiontId) {
        Buy buy = new Buy(hydrobiontId, customUserDetailsService.getNowBasketNum());
        buyRepository.save(buy);
        return buy;
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
