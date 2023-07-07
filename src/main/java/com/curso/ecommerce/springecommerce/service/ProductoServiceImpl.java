
package com.curso.ecommerce.springecommerce.service;

import com.curso.ecommerce.springecommerce.model.Producto;
import com.curso.ecommerce.springecommerce.repository.ProductoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired  //Nos está diciendo que estamos insertando un objeto a esta clase
    private ProductoRepository productoRepository;
    
    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id); 
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto); 
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id); 
    }
    
}
