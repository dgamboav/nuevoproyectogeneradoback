package com.dgamboav.nuevoproyectogeneradoback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica la configuración a todas las rutas
                .allowedOrigins("http://localhost:5173","http://34.135.44.219:80","http://34.135.44.219")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos los headers
                .allowCredentials(true) // Permite el envío de cookies y credenciales
                .maxAge(3600); // Tiempo de vida de la pre-petición en segundos
    }
}
