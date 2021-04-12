
package com.pruebaalmacen.springboot.backend.Almacen.services;

import com.pruebaalmacen.springboot.backend.Almacen.dao.IPedidoDao;
import com.pruebaalmacen.springboot.backend.Almacen.entitys.Pedido;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoServiceImpl implements IPedidoService {
    @Autowired
    private IPedidoDao pedidoDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> findById(Long id) {
        return pedidoDao.findById(id);
    }

    @Override
    @Transactional
    public Pedido save(Pedido pedido) {
        return pedidoDao.save(pedido);
    }
}
