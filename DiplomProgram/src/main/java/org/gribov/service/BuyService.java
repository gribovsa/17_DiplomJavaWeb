package org.gribov.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.gribov.model.Buy;
import org.gribov.model.Hydrobiont;
import org.gribov.repository.BuyRepository;
import org.gribov.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

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
    @Autowired
    private HydrobiontService hydrobiontService;
    public static long sequence = 1L;

    /**
     * Метод создания покупки
     */
    public void createBuy(Long hydrobiontId) {
        Buy buy = new Buy(null, hydrobiontId, customUserDetailsService.getNowBasketNum());
        Hydrobiont hydrobiont = hydrobiontService.getHydrobiontById(hydrobiontId);
        //проверим требуемое количество товара на складе
        if (hydrobiont.getQuantity() >0){
            hydrobiont.setNewRating(hydrobiont.getRating()); //увеличиваем рейтинг покупке
            hydrobiont.setNewQuantity(hydrobiont.getQuantity());//уменьшаем количество на складе
            buyRepository.save(buy);
        }
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

    /**
     * Метод возвращает стоимость всех покупок по номеру корзины basket_num
     */
    public Float getTotalPrice(Long basketNum){
        List<Buy> buyList = buyRepository.findByBasketNum(basketNum);
        float sum = 0F;
        for (Buy buy : buyList) {
            Hydrobiont hydrobiont = hydrobiontService.getHydrobiontById(buy.getHydrobiontId());
            sum = sum + hydrobiont.getPrice();
        }
        return sum;
    }

    /**
     * Метод удаляет товар из корзины по номеру товара
     */
    public void deleteBuyById(Long id){
        buyRepository.deleteById(id);
    }

}
