
package com.pruebaalmacen.springboot.backend.Almacen.dao;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteDao extends CrudRepository<Cliente, Long>{
    
}