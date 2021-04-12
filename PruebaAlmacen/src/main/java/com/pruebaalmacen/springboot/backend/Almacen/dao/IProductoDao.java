
package com.pruebaalmacen.springboot.backend.Almacen.dao;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoDao extends CrudRepository<Producto, Long> {
    
}
