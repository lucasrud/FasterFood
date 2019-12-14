package de.fasterfood.fasterfood.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/","/api/fasterfood/order","/fasterfood", "/api/price/meals",
                "/api/price/ingredients", "/api/fasterfood/addMeal", "/api/fasterfood/deleteMeal", "/api/finance",
                "/api/ingredients/delete", "/api/ingredients/checkdependencies").permitAll();
        http.cors().and().csrf().disable();
    }
}
