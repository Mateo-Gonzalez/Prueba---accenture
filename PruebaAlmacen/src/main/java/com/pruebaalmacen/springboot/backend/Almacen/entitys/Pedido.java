package com.pruebaalmacen.springboot.backend.Almacen.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Pedidos")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    
    @Column(length = 20, nullable = false)
    private String estado;

    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;

    @Column(length = 300, nullable = true)
    private String descripcion;

    @JsonIgnoreProperties(value = {"pedidos", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPedido")
    private List<Envio> envios;

    
    public Pedido() {
        this.envios = new ArrayList<>();
    }

    // Generar la fecha del pedido
    @PrePersist
    public void prePersist() {
        this.fechaPedido = new Date();
    }

    public Double valorSubTotal() {
        Double subTotal = 0.00;

        for (Envio linea : envios) {
            subTotal += linea.valorEnvio();
        }

        return subTotal;
    }

    public Double valorIva() {
        Double porcentajeIva = 0.19;
        Double iva = this.valorSubTotal() * porcentajeIva;
        return iva;
    }

    public Double valorTotal() {
        Double aux = this.valorTotal() + this.valorIva() + this.valorDomicilio();
        Double total = 0.00;

        if (this.estado.equals("cancelado1")) {
            total = aux * 0.10;
        } else if (this.estado.equals("cancelado2")) {
            total = 0.00;
        }

        return total;
    }

    public Double valorDomicilio() {
        Double valorDomicilio = 0.00;
        Double subTotal = this.valorSubTotal();

        if (subTotal > 70000 && subTotal < 100000) {
            valorDomicilio = 10000.00;
        } else if (subTotal > 100000) {
            valorDomicilio = 0.00;
        }

        return valorDomicilio;
    }

    private static final long serialVersionUID = 3L;
}
