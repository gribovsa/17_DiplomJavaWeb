package org.gribov.service;

import org.gribov.dto.UserDto;
import org.gribov.model.Hydrobiont;
import org.gribov.model.Role;
import org.gribov.model.User;
import org.gribov.repository.RoleRepository;
import org.gribov.repository.UserRepository;
import org.gribov.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс - сервис, вся логика работы с пользователями
 */
@Service
public class UserService {
    public static long sequence = 1L;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;



    /**
     * Метод создания и сохранения пользователя
     * пользователь получен в формате dto
     * сохранён в преобразованном формате (единое наименование и зашифрованный пароль)
     */
    public User saveUserDto(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setNowBasketNum(customUserDetailsService.getUniqueBasketNum()); //задаём уникальный номер корзины
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
        return user;
    }



    /**
     * Метод ищет пользователя по id
     */
    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);

    }

    /**
     * Метод ищет и возвращает пользователя по email
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * Метод ищет всех пользователей,
     * возвращает пользователей в формате dto
     */
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    /**
     * Метод удаляет пользователя
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        //добавить функционал - очистить запись в таблице users_roles
    }


    /**
     * Метод конвертирует пользователя в формат dto
     */
    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    /**
     * Метод добавляет роль (переименовать)
     */
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}
