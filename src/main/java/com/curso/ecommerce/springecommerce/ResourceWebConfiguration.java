
package com.curso.ecommerce.springecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//Clase de configuración para apuntar desde cualquier lugar a los recursos de imágenes
public class ResourceWebConfiguration implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //directorio + indicación a donde debería apuntar
       registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
    }
    
}
