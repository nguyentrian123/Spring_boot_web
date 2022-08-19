package com.laptrinhjavaweb.config;

import com.laptrinhjavaweb.security.CustomSuccessHandler;
import com.laptrinhjavaweb.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean // 1 phần có sẵn của security kiểu mã hóa là Bcrypt
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override // nơi xác thực username password
    protected void configure(AuthenticationManagerBuilder auth) { // AuthenticationManagerBuilder là nơi quản lý các chứng(xác) thực 
        auth.authenticationProvider(authenticationProvider());
    }

    @Override // bắt ta chứng thực or không  khi vào 1 cái url nào đó .
    protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable()
                .authorizeRequests() // đường dẫn phải đc yêu cầu chứng thực luôn luôn có
                        .antMatchers("/admin/**").hasRole("ADMIN") // nếu path = /admin/** thì phải có role là ADMIN ms cho vào 
                        .antMatchers("/login", "/resource/**", "/trang-chu", "/api/**").permitAll() // các paths = path như đã định nghĩa thì cho vào 
                         //  /resource/**  là cho phép vào resource load các file css, js ...  
                .and() // và form login phải là trang login mà mình custom. username và password này là ta nhập ở ngoài  nó sẽ sánh với thằng trong  AuthenticationManagerBuilder
                .formLogin().loginPage("/login").usernameParameter("j_username").passwordParameter("j_password").permitAll()
                .loginProcessingUrl("/j_spring_security_check") // path có sẵn của SS nó nhận username password vào  và trong path này đã khai báo AuthenticationManagerBuilder
                .successHandler(myAuthenticationSuccessHandler()) // nếu đúng thì nhảy tới page được định nghĩa
                .failureUrl("/login?incorrectAccount").and() // sai thì về lại form login và hiển thị thông báo và 
                .logout().logoutUrl("/logout").deleteCookies("JSESSIONID") // khi logout thì xóa cookiees
                .and().exceptionHandling().accessDeniedPage("/access-denied").and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login?sessionTimeout");
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomSuccessHandler();
    }
}
