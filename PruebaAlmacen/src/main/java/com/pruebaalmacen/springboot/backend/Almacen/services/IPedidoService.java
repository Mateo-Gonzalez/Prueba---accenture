package com.pruebaalmacen.springboot.backend.Almacen.services;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Pedido;
import java.util.Optional;

public interface IPedidoService {

    public Optional<Pedido> findById(Long id);

    public Pedido save(Pedido pedidop);
}
