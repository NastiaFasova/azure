package com.epam.demo.models.dto.response;

import lombok.Data;

import javax.persistence.Transient;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    private String captcha;
    private String hiddenCaptcha;
    private String realCaptcha;
}
