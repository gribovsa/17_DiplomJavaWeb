package org.gribov.security;

import org.gribov.dto.UserDto;
import org.gribov.model.User;

import java.util.List;

public interface UserService {
    void saveUserDto(UserDto userDto);
    User findByEmail(String email);
    List<UserDto> findAllUsers();
    void saveUser(User user);
}



