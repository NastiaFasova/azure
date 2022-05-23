package com.epam.demo.models.dto.request;

import com.epam.demo.models.Role;
import com.epam.demo.validation.EmailValidation;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @Column(unique = true)
    @EmailValidation
    private String email;
    private String patronymic;
    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password should have min 4 characters")
    private String password;
    private Set<Role> roles;
    private String captcha;
    private String hiddenCaptcha;
    private String realCaptcha;
}
