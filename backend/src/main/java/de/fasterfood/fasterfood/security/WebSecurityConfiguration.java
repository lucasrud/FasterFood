package de.fasterfood.fasterfood.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/", "/meals", "/api/meals/order", "/api/meals/price",
                "/api/meals/addMeal", "/api/meals/deleteMeal", "/api/finance", "/api/ingredients",  "/api/ingredients/price",
                "/api/ingredients/delete", "/api/ingredients/checkdependencies", "/api/order").permitAll();
        http.cors().and().csrf().disable();

//
//        http.httpBasic();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.authorizeRequests().anyRequest().permitAll();
//        http.exceptionHandling()
//                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
////
//        http.logout().logoutUrl("/api/logout")
//                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
////
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
