package com.empleate;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    /* Los siguientes métodos son para incorporar el tema de internacionalización en el proyecto */

 /* localeResolver se utiliza para crear una sesión de cambio de idioma */
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");

        return slr;
    }

    /* localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    //Bean para poder acceder a los Messages.properties en código Java...
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login"); // Redirige a la página de login
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/perfil/muestra").setViewName("/perfil/muestra");
        registry.addViewController("/perfil/editar").setViewName("/perfil/editar");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

//    /* El siguiente método se utiliza para completar la clase no es 
//    realmente funcional, la próxima semana se reemplaza con usuarios de BD */
//    @Bean
//    public UserDetailsService users() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}admin123")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails empleador = User.builder()
//                .username("empresa1")
//                .password("{noop}empresa123")
//                .roles("EMPLEADOR")
//                .build();
//
//        UserDetails empleado = User.builder()
//                .username("empleado1")
//                .password("{noop}empleado123")
//                .roles("EMPLEADO")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, empleador, empleado);
//    }
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                // Rutas públicas
                .requestMatchers( "/errores/**", "/registro/**", "/js/**", "/webjars/**", "/error", "/perfil/**").permitAll()
                // Rutas exclusivas para EMPLEADORES
                .requestMatchers(
                        "/", "/index","/empleo/nuevo", "/empleo/guardar", "/empleo/modificar/**", "/empleo/eliminar/**",
                        "/usuario/modificar/**", "/usuario/eliminar/**", "/empresa/nuevo", "/empresa/guardar", "/perfil/editar", "/usuario/editar"
                ).hasRole("EMPLEADOR")
                // Rutas compartidas por roles específicos
                .requestMatchers(
                        "/", "/index","/empleo/listado", "/empresa/listado", "/usuario/perfil", "/usuario/guardar",
                        "/usuario/perfil/editar", "/usuario/perfil/guardar", "/perfil/editar", "/usuario/editar", "/usuario/perfil/editar/1"
                ).hasAnyRole("EMPLEADOR", "ESTUDIANTE")
                // Rutas exclusivas para ESTUDIANTES
                .requestMatchers("/postulacion/aplicar/**").hasRole("ESTUDIANTE")
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
                )
                .logout((logout) -> logout
                .permitAll()
                );

        return http.build();
    }

}
