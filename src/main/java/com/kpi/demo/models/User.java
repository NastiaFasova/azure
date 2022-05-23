package com.epam.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    @ManyToMany
    private Set<Role> roles;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Publication> publications;
    @OneToOne(cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Bill bill;
    private boolean locked;
    @OneToOne(cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Subscription subscription;
    @OneToOne(cascade =  CascadeType.ALL,
            mappedBy = "user")
    private License license;
    private String captcha;
    private String hiddenCaptcha;
    private String realCaptcha;
}
