package com.example.demo.security;

import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(

        securedEnabled = true,
        jsr250Enabled =true,
        prePostEnabled = true

)
public class SecurityConfig extends WebSecurityConfigurerAdapter {



@Autowired
private UserServices userServices;
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
@Bean
public JwtAuthenticationFilter jwtAuthenticationFilter(){
    return new JwtAuthenticationFilter();
}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServices).passwordEncoder(bCryptPasswordEncoder);      //type we want to use  for authentication
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    private JwtAuthEntryPoint authEntryPoint;
    @Override
    protected void configure(HttpSecurity httpSecurity  )throws Exception{
                                                                                        //it shows error if you not authorized
httpSecurity.cors().and ().csrf().disable().exceptionHandling().authenticationEntryPoint(authEntryPoint).and().sessionManagement()
                                                                                          //dont authorize
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/**").permitAll().anyRequest().authenticated();
httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
