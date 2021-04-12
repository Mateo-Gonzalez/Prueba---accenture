package com.pruebaalmacen.springboot.backend.Almacen.services;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Producto;
import java.util.Optional;

public interface IProductoService {

    public Iterable<Producto> findAll();

    public Optional<Producto> findById(Long id);

    public Producto save(Producto producto);

    public void deleteById(Long id);
}
