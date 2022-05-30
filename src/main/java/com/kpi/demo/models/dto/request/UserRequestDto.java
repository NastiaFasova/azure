package com.kpi.demo.models.dto.request;

import com.kpi.demo.models.Role;
import com.kpi.demo.validation.EmailValidation;
import lombok.Data;
import javax.persistence.Column;
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
    @Size(min = 4, message = "Password should have min 4 characters")
    private String password;
    private Set<Role> roles;
}
