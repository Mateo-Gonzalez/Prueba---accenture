
package com.pruebaalmacen.springboot.backend.Almacen.services;

import com.pruebaalmacen.springboot.backend.Almacen.dao.IProductoDao;
import com.pruebaalmacen.springboot.backend.Almacen.entitys.Producto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl  implements IProductoService{
     @Autowired
    private IProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Producto> findAll() {
        return productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return productoDao.findById(id);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoDao.deleteById(id);
    }
}
