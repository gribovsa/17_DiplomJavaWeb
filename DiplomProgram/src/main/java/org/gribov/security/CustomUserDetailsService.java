
package org.gribov.security;

import org.gribov.model.Buy;
import org.gribov.model.Role;
import org.gribov.model.User;
import org.gribov.repository.BuyRepository;
import org.gribov.repository.UserRepository;
import org.gribov.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Компонент - класс в котором обрабатываются данные о пользователе
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BuyRepository buyRepository;

    private User user; //инициализируем текущего пользователя


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод позволяет получить email пользователя из WEB формы, и найти его в
     * user репозитории и вернуть объект UserDetail где хранится основная информация о пользователе
     * @param email электронная почта
     * @return объект UserDetail где хранится основная информация о пользователе
     * @throws UsernameNotFoundException пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        user = userRepository.findByEmail(email);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }

    /**
     * Метод возвращает номер корзины текущего пользователя
     */
    public Long getNowBasketNum() {
        return user.getNowBasketNum();
    }

    /**
     * Метод возвращает id текущего пользователя
     */
    public Long getNowUserId() {
        return user.getId();
    }

    /**
     * Метод задаёт пользователю новый уникальный номер корзины
     * требуется при создании нового заказа
     */
    public void setNewUniqueBasketNumToUser(){
        user.setNowBasketNum(getUniqueBasketNum());
        userRepository.save(user); //проверить
    }

    /**
     * Метод генерации нового уникального номера корзины
     */
    public Long getUniqueBasketNum() {
        Long newBasketNum = 1L;
        List<Buy> buyList = buyRepository.findAll(); //получили все покупки
        for (Buy returnedBuy : buyList) {
            if (Objects.equals(returnedBuy.getBasketNum(), newBasketNum)){
                newBasketNum++;
            }
        }
        return newBasketNum;
    }
}


