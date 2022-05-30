package com.kpi.demo.models.mapper;

import com.kpi.demo.models.User;
import com.kpi.demo.models.dto.request.UserRequestDto;
import com.kpi.demo.models.dto.response.UserResponseDto;
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
        return userResponseDto;
    }

    public User getUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        user.setPatronymic(userRequestDto.getPatronymic());
        user.setPassword(userRequestDto.getPassword());
        user.setRoles(userRequestDto.getRoles());
        return user;
    }
}
