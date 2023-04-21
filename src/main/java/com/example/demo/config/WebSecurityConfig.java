package com.example.demo.config;

import com.example.demo.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserService customUserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //不拦截静态资源
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers()
                .frameOptions().disable()
                .and().csrf()
                .ignoringAntMatchers( "/user/**")
                .and().formLogin()
                .loginPage("/user/login")
                .permitAll()
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    System.out.println("登陆成功处理==============");
                    //跳转到首页
                    httpServletResponse.sendRedirect("/");
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    System.out.println("登陆失败处理=============");
                    //返回到登陆页面
                    httpServletResponse.sendRedirect("/user/login");
                })
                .and().logout()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(((httpServletRequest, httpServletResponse, authentication) -> {
                    System.out.println("登出成功处理=============");
                    httpServletResponse.sendRedirect("/user/login");
                }))
                .and().authorizeRequests()
                .antMatchers("/user/register","/users/list").permitAll()
                .antMatchers("/admin/**","/users/id").hasRole("ADMIN")
                .anyRequest().authenticated();
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder);
    }
}
