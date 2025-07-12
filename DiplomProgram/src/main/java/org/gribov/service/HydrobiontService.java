package org.gribov.service;

import org.gribov.model.Hydrobiont;
import org.gribov.repository.HydrobiontRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс - сервис, вся логика работы с гидробионтами
 */
@Service
public class HydrobiontService {
    @Autowired
    private HydrobiontRepository hydrobiontRepository;


    /**
     * Метод возвращает все гидробионты
     * (сортировка по полю id)
     */
    public List<Hydrobiont> getAllHydrobiont() {
        return hydrobiontRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    /**
     * Метод ищет гидробионта по id
     */
    public Hydrobiont getHydrobiontById(long id) {
        return hydrobiontRepository.findById(id).orElse(null);
    }

    /**
     * Метод добавляет гидробионта
     */
    public Hydrobiont addHydrobiont(Hydrobiont hydrobiont) {
        return hydrobiontRepository.save(hydrobiont);
    }

    /**
     * Метод обновляет гидробионта
     */
    public Hydrobiont updateHydrobiontById(Long id, Hydrobiont hydrobiont) {
        Hydrobiont updateHydrobiont = getHydrobiontById(id);
        updateHydrobiont.setDirection(hydrobiont.getDirection());
        updateHydrobiont.setType(hydrobiont.getType());
        updateHydrobiont.setNameGeneric(hydrobiont.getNameGeneric());
        updateHydrobiont.setNameSpecies(hydrobiont.getNameSpecies());
        updateHydrobiont.setQuantity(hydrobiont.getQuantity());
        updateHydrobiont.setPrice(hydrobiont.getPrice());
        updateHydrobiont.setPhoto(hydrobiont.getPhoto());
        updateHydrobiont.setLevel(hydrobiont.getLevel());
        updateHydrobiont.setRating(hydrobiont.getRating());
        return hydrobiontRepository.save(updateHydrobiont);
    }

    /**
     * Метод удаляет гидробионта
     */
    public void deleteHydrobiontById(Long id) {
        hydrobiontRepository.deleteById(id);
    }

}
