package com.contrato.config;

import com.contrato.services.impl.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginSucessHandler sucessHandler;

    protected void configure(HttpSecurity http) throws Exception {
        try {
            http.authorizeRequests()
                    // Para permitir nuestro js y css (en nuestra carpeta static)
                    .antMatchers("/*.js", "/*.css").permitAll()
                    // Para que solo un usuario logueado con esos roles acceda a /
                    .antMatchers("/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                    // Para que solo un usuario logueado con esos roles acceda al modulo de empleados
                    .antMatchers("/empleados").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                    .antMatchers("/empleados/**").access("hasRole('ROLE_ADMIN')")
                    // Para que solo un usuario logueado con esos roles acceda al modulo de departamentos
                    .antMatchers("/departamentos").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                    .antMatchers("/departamentos/**").access("hasRole('ROLE_ADMIN')")
                    // Para que solo un usuario logueado con esos roles acceda al modulo de postulantes
                    .antMatchers("/postulantes").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                    .antMatchers("/postulantes/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                    // Para que solo un usuario con ROLE_ADMIN pueda acceder al modulo de usuarios
                    .antMatchers("/usuarios").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/usuarios/**").access("hasRole('ROLE_ADMIN')")
                    // Lógica del login (configurado en LoginSucessHandler)
                    .and().formLogin().successHandler(sucessHandler).loginPage("/login").loginProcessingUrl("/login")
                    // Si el login es exitoso, retorna a /
                    .defaultSuccessUrl("/").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
                    // Si el usuario va a una ruta sin acceso, devuelve a /error_403 (Configurado en MvcConfig)
                    .and().exceptionHandling().accessDeniedPage("/error403");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
