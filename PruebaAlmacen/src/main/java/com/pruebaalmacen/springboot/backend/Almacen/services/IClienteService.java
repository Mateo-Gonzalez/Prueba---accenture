
package com.pruebaalmacen.springboot.backend.Almacen.services;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Cliente;
import java.util.Optional;


public interface IClienteService {
    public Iterable<Cliente> findAll();	
	
	public Optional<Cliente> findById(Long id);
	
	public Cliente save(Cliente usuario);
	
	public void deleteById(Long id);
}
