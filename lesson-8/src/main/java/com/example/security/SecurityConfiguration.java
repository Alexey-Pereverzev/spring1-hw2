package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void authConfigure(AuthenticationManagerBuilder auth,
                              ClientAuthService clientDetailService,
                              PasswordEncoder passwordEncoder) throws Exception {
        auth.inMemoryAuthentication()                       //  аутентификация в памяти - для учебных целей
                .withUser("mem_client")
                .password(passwordEncoder.encode("password"))
                .roles("SUPER_ADMIN")
        ;

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(clientDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(provider);
    }

    @Configuration
    @Order(2)
    public static class UiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()    // доступно всем
                    .antMatchers("/product/**").permitAll()         // доступно всем
                    .antMatchers("/client/**").authenticated()        // те, кто авторизовался
                    .and()
                    .formLogin()
                    .defaultSuccessUrl("/product");             // авторизуемся через логин-форму
        }
    }
}
