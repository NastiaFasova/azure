package com.kpi.demo.config;

import com.kpi.demo.security.CipherEncoderImpl;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/register", "/logout", "/js/**", "/css/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/create/**", "/update/**",  "user/**", "/delete/**")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/subscription/**", "/user/publications/**",
                        "/bill")
                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-error")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new CipherEncoderImpl();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("static/**");
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
