package presentation.demo.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import presentation.demo.services.serviceImpl.UserServiceImpl;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder encoder;
    private final UserServiceImpl userServiceImpl;

    public SecurityConfiguration(PasswordEncoder encoder, UserServiceImpl userServiceImpl) {
        this.encoder = encoder;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/index","/home","/css/**","/js/**","/images/**","/users/login").permitAll()
                .antMatchers("/practices/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/users/login").successForwardUrl("/redirect");
    }
}
