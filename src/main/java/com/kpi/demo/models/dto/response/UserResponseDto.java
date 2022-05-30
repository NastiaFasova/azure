package com.kpi.demo.models.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
}
