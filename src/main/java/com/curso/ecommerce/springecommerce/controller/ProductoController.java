package com.curso.ecommerce.springecommerce.controller;

import com.curso.ecommerce.springecommerce.model.Producto;
import com.curso.ecommerce.springecommerce.model.Usuario;
import com.curso.ecommerce.springecommerce.service.ProductoService;
import com.curso.ecommerce.springecommerce.service.UploadFileService;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    //Utilizamos esta notación para que no tengamos que instanciar el objeto sino que lo hace Spring
    @Autowired
    private ProductoService productoService;
    //Este objeto nos sirve para testear 
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    //Este objeto Model lleva info del back a la vista
    public String show(Model model) {
        //En los parámetros le asigno un nombre y el valor que voy a enviar a través de ese atributo
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        //Nos imprime por consola esta cadena y el ToString del objeto Producto
        //Es una buena práctica, no así utilizar printOutLine para chequear si viaja correctamente la info 
        //y en qué clase estoy haciendola impersión 
        //Las llaves indican que le sigue un objeto o una variable
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(usuario);
        //imagen
        if (producto.getId() == null) {//Cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } 
        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    //La anotación PathVariable mapea la variable de la URL y la guarda en la variable continua a dicha anotación
    public String edit(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("Producto buscado: {}", producto);
        model.addAttribute("producto", producto);

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
            //Obtenemos el producto
            p = productoService.get(producto.getId()).get();
            
        if (file.isEmpty()) {//Editamos el producto pero no cambiamos la imagen
            
            producto.setImagen(p.getImagen());
        } else {//Cuando también queremos cambiar la imagen
            
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleteImage(p.getImagen());
            }

            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Producto p = new Producto();
        p = productoService.get(id).get();
        //Debo eliminar la imagen para que no quede en el servidor
        //Eliminar cuando no sea la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleteImage(p.getImagen());
        }
        //Luego borro el registro
        productoService.delete(id);
        return "redirect:/productos";
    }

}
