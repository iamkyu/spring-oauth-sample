package io.iamkyu.config;

import io.iamkyu.service.SocialUsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author Kj Nam
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //@formatter:off
        http
            .csrf().disable()
            .anonymous().and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .loginProcessingUrl("/login/authenticate")
            .failureHandler(new CustomLoginFailureHandler())
        .and()
            .logout()
            .deleteCookies("SESSION")
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
        .and()
            .authorizeRequests()
            .antMatchers(
                    "/auth/**",
                    "/login",
                    "/error",
                    "/signup",
                    "/css/**",
                    "/js/**",
                    "/image/**",
                    "/console/**"
            ).permitAll()
            .antMatchers("/**").hasRole("USER")
        // h2 console 을 위한 설정
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .apply(new SpringSocialConfigurer());
        //@formatter:on
    }

    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SocialUsersDetailService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}
