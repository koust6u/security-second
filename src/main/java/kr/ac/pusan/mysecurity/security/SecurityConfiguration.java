package kr.ac.pusan.mysecurity.security;

import kr.ac.pusan.mysecurity.security.handler.CustomAccessSuccessHandler;
import kr.ac.pusan.mysecurity.security.provider.AuthenticationProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProviderService authenticationProvider;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/user").hasAuthority("USER")
                        .requestMatchers("/manager").hasAuthority("MANAGER")
                        .requestMatchers("/", "/login**", "/sign-up", "/home").permitAll()
                        .anyRequest().authenticated())

                .formLogin((login) -> login
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/login_proc")
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler)
                        .defaultSuccessUrl("/home")
                        .permitAll())
                .authenticationProvider(authenticationProvider)

                .logout(l -> l
                        .logoutUrl("/logout").permitAll()
                        .logoutSuccessHandler((request,response,auth) ->{
                            response.sendRedirect("/login2");
                        }).deleteCookies("remember-me"))
                .build();

    }

}
