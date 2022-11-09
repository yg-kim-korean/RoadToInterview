package com.server.RoadToInerview.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/",true)
                .permitAll()
                .and().logout();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().anyRequest();
//        web.ignoring().antMatchers("/"); //홈 화면 혹은 로그인화면같이 보여줘야 하는 페이지들에 접근하는거는 혀ㅓ용하도록함.
    }
}
