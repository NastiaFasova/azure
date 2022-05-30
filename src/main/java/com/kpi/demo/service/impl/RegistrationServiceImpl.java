package com.kpi.demo.service.impl;

import com.kpi.demo.models.Bill;
import com.kpi.demo.models.License;
import com.kpi.demo.models.Role;
import com.kpi.demo.models.Subscription;
import com.kpi.demo.models.User;
import com.kpi.demo.service.RegistrationService;
import com.kpi.demo.service.RoleService;
import com.kpi.demo.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public RegistrationServiceImpl(UserService userService, @Qualifier("cipherEncoderImpl") PasswordEncoder passwordEncoder,
                                   RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User register(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPatronymic(user.getPatronymic());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        LOGGER.info("User" + newUser.getEmail() + "password " + user.getPassword());
        if (user.getRoles() == null) {
            Role role = roleService.findByRoleName("USER");
            LOGGER.info("Role was successfully retrieved by title");
            newUser.setRoles(Set.of(role));
        }
         else {
            newUser.setRoles(user.getRoles());
        }
        Bill bill = new Bill(0D);
        Subscription subscription = new Subscription();
        License license = new License(LocalDate.now());
        license.setUser(user);
        bill.setUser(newUser);
        subscription.setUser(newUser);
        newUser.setBill(bill);
        newUser.setSubscription(subscription);
        user.setLicense(license);
        userService.save(newUser);
        LOGGER.info("User was successfully registered");
        return newUser;
    }
}
