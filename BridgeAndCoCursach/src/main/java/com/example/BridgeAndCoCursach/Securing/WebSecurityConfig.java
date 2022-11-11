package com.example.BridgeAndCoCursach.Securing;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.servlet.session.SessionConcurrencyDsl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Sha512PasswordEncoder();
    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("user")
//
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                        .
                antMatchers("/Authorization/Login", "/Authorization/Registration",
                        "/api/account/accounts", "/api/account/{id}",
                        "/api/user/users","/api/user/{id}", "/api/user/add",
                        "/api/role/roles","/api/role/{id}",
                        "/api/supplier/suppliers","/api/supplier/{id}", "/api/supplier/add",
                        "/api/supply/supplies","/api/supply/{id}", "/api/supply/add",
                        "/api/shipment/shipments","/api/shipment/{id}", "/api/shipment/add",
                        "/api/storage/storages","/api/storage/{id}", "/api/storage/add",
                        "/api/pathing/pathings","/api/pathing/{id}", "/api/pathing/add",
                        "/api/ordershipment/ordershipments","/api/ordershipment/{id}", "/api/ordershipment/add","/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/Authorization/Login").permitAll()
                .usernameParameter("username")

                .and()
                .formLogin(formLogin -> formLogin
                        .successHandler(new AuthentificationSuccess())
                )
                .sessionManagement().maximumSessions(1).and()
                .and()
                .logout().invalidateHttpSession(true).permitAll()
                .and()
                .csrf().disable().cors().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().
                dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password,active FROM account WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, ur.role FROM account u INNER JOIN role ur ON u.id=ur.user_id WHERE username=?")

        ;

    }



}