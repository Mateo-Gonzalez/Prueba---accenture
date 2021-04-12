package com.pruebaalmacen.springboot.backend.Almacen.controllers;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Envio;
import com.pruebaalmacen.springboot.backend.Almacen.entitys.Pedido;
import com.pruebaalmacen.springboot.backend.Almacen.services.IPedidoService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    
    @Autowired
    private IPedidoService pedidoService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable(value = "id") Long pedidoId) {
        Optional<Pedido> oPedido = pedidoService.findById(pedidoId);

        if (!oPedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Pedido pedidoDetalles, @PathVariable(value = "id") Long pedidoId) {

        Optional<Pedido> oPedido = pedidoService.findById(pedidoId);

        if (!oPedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<Envio> lineas = pedidoDetalles.getEnvios();

        Double precioAnterior = oPedido.get().valorSubTotal();

        //Calcular el nuevo subtotal del pedido
        Double nuevoSubTotal = 0.00;

        for (Envio linea : lineas) {
            nuevoSubTotal += linea.valorEnvio();
        }

        //Calcular hace cuanto se realizo el pedido
        Long horasPedido = this.calcularHorasPedido(oPedido.get().getFechaPedido());

        //Calcular si el pedido fue hace menos de 5 horas
        if (horasPedido > 5) {
            return ResponseEntity.badRequest().build();
        }

        //Verificar si el sub total de la factura es mayor o igual que el anterior
        if (nuevoSubTotal < precioAnterior) {
            return ResponseEntity.badRequest().build();
        }

        //Altualizar nuevos datos del pedido
        oPedido.get().setDescripcion(pedidoDetalles.getDescripcion());
        oPedido.get().setEnvios(pedidoDetalles.getEnvios());

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(oPedido.get()));
    }
// Cancelar Pedido

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancel(@PathVariable(value = "id") Long orderId) {
        Optional<Pedido> oPedido = pedidoService.findById(orderId);

        if (!oPedido.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Verificacion de las 12 horas para cancelar
        Long hoursAgo = this.calcularHorasPedido(oPedido.get().getFechaPedido());

        if (hoursAgo > 12) {
            oPedido.get().setEstado("cancelado1");
        } else {
            oPedido.get().setEstado("cancelado2");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(oPedido.get()));
    }

    //Metodo para calcular hace cuantas horas el usuario o cliente realizo su pedido
    public Long calcularHorasPedido(Date fechaPedido) {

        Calendar fechaActual = Calendar.getInstance();

        Long diferenciaMilisegundos = Math.abs(fechaActual.getTimeInMillis() - fechaPedido.getTime());

        Long horas = TimeUnit.MILLISECONDS.toHours(diferenciaMilisegundos) + 1;

        return horas;
    }
}
