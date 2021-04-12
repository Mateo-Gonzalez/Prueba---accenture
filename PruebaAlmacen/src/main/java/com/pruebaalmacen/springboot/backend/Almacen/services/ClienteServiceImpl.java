
package com.pruebaalmacen.springboot.backend.Almacen.services;

import com.pruebaalmacen.springboot.backend.Almacen.dao.IClienteDao;
import com.pruebaalmacen.springboot.backend.Almacen.entitys.Cliente;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements IClienteService {
    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly=true)
    public Iterable<Cliente> findAll() {
        
        return clienteDao.findAll();
    }
    
    @Override
    @Transactional(readOnly=true)
    public Optional<Cliente> findById(Long id) {
        
        return clienteDao.findById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente usuario) {
        
        return clienteDao.save(usuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clienteDao.deleteById(id);

    }

}
