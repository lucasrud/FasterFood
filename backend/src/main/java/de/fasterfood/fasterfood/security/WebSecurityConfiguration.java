package de.fasterfood.fasterfood.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/", "/meals", "/api/meals/order", "/api/meals/price",
                "/api/meals/addMeal", "/api/meals/deleteMeal", "/api/finance", "/api/ingredients/price",
                "/api/ingredients/delete", "/api/ingredients/checkdependencies").permitAll();
        http.cors().and().csrf().disable();
    }
}
