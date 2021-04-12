
package com.pruebaalmacen.springboot.backend.Almacen.dao;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoDao extends CrudRepository<Pedido, Long>{
    
}
