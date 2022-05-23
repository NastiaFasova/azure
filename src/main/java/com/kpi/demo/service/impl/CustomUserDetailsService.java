package com.epam.demo.service.impl;

import com.epam.demo.exception.AuthenticationException;
import com.epam.demo.models.User;
import com.epam.demo.service.LicenseService;
import com.epam.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final LicenseService licenseService;

    @Autowired
    public CustomUserDetailsService(UserService userService, LicenseService licenseService) {
        this.userService = userService;
        this.licenseService = licenseService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getByEmail(login);
        if (licenseService.isExpired()) {
            throw new AuthenticationException("Your license has expired!");
        }
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user != null && !user.isLocked()) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(login);
            userBuilder.password(user.getPassword());
            String[] roles = user.getRoles().stream()
                    .map(r -> r.getRoleName().name())
                    .toArray(String[]::new);
            userBuilder.roles(roles);
            return userBuilder.build();
        }
        throw new UsernameNotFoundException("User is not found.");
    }
}
