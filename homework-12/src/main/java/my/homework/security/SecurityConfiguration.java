package my.homework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService, PasswordEncoder encoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("sadmin")
                .password(encoder.encode("sadmin"))
                .roles("SUPER_ADMIN")
                .and()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .and()
                .withUser("guest")
                .password(encoder.encode("guest"))
                .roles("GUEST");
        auth.userDetailsService(userDetailsService);
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/rest/**")
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // ?????????????????????? ?????? ???????????? ????????????????! ???????? ?????? ?????????????? ????????????, ???? ?????????????????????? ?????????? ?????????????????????? ?? ????????????.

        }
    }


        @Configuration
        @Order(2)
    public static class UiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Value("${server.servlet.context-path}")
        private String contextPath;
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/access_denied").authenticated()
                    //.antMatchers("/user/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .antMatchers("/user").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .antMatchers("/user/**").hasAnyRole("SUPER_ADMIN")
                    .and()
                    .formLogin()
                    //.loginPage("/login")
                    //.defaultSuccessUrl("/")
                    .successHandler((req, resp, auth) -> {
                        Set<String> auths = auth.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet());
                        if (auths.contains("ROLE_ADMIN") || auths.contains("ROLE_SUPER_ADMIN")) {
                            resp.sendRedirect(contextPath + "user");
                        } else {
                            resp.sendRedirect(contextPath);
                        }
                    })
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");
        }
    }
}
