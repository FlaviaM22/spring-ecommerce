
package com.curso.ecommerce.springecommerce.controller;



import com.curso.ecommerce.springecommerce.model.Producto;
import com.curso.ecommerce.springecommerce.model.Usuario;
import com.curso.ecommerce.springecommerce.service.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    //Utilizamos esta notación para que no tengamos que instanciar el objeto sino que lo hace Spring
    @Autowired
    private ProductoService productoService;
    //Este objeto nos sirve para testear 
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @GetMapping("")
    public String show(){
        return "productos/show";
    }
    
    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }
    
    @PostMapping("/save")
    public String save(Producto producto){
        //Nos imprime por consola esta cadena y el ToString del objeto Producto
        //Es una buena práctica, no así utilizar printOutLine para chequear si viaja correctamente la info 
        //y en qué clase estoy haciendola impersión 
        //Las llaves indican que le sigue un objeto o una variable
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(usuario);
        productoService.save(producto);
        return "redirect:/productos";
    }
    
}
