
package com.curso.ecommerce.springecommerce.repository;

import com.curso.ecommerce.springecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
