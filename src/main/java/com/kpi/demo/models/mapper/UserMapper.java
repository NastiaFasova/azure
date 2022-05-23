package com.epam.demo.models.mapper;

import com.epam.demo.models.User;
import com.epam.demo.models.dto.request.UserRequestDto;
import com.epam.demo.models.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        userResponseDto.setPatronymic(user.getPatronymic());
        userResponseDto.setCaptcha(user.getCaptcha());
        userResponseDto.setRealCaptcha(user.getRealCaptcha());
        userResponseDto.setHiddenCaptcha(user.getHiddenCaptcha());
        return userResponseDto;
    }

    public User getUserFromUserResponseDto(UserResponseDto userResponseDto) {
        User user = new User();
        user.setId(userResponseDto.getId());
        user.setEmail(userResponseDto.getEmail());
        user.setName(userResponseDto.getName());
        user.setSurname(userResponseDto.getSurname());
        user.setPatronymic(userResponseDto.getPatronymic());
        user.setCaptcha(userResponseDto.getCaptcha());
        user.setRealCaptcha(userResponseDto.getRealCaptcha());
        user.setHiddenCaptcha(userResponseDto.getHiddenCaptcha());
        return user;
    }

    public User getUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        user.setPatronymic(userRequestDto.getPatronymic());
        user.setPassword(userRequestDto.getPassword());
        user.setRoles(userRequestDto.getRoles());
        user.setCaptcha(userRequestDto.getCaptcha());
        user.setRealCaptcha(userRequestDto.getRealCaptcha());
        user.setHiddenCaptcha(userRequestDto.getHiddenCaptcha());
        return user;
    }
}
