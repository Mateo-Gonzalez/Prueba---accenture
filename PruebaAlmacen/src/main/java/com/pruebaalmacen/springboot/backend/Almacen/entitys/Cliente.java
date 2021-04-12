package com.pruebaalmacen.springboot.backend.Almacen.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(length = 15, nullable = false)
    private String documento;

    @Column(length = 70, nullable = false)
    private String nombres;

    @Column(length = 70, nullable = false)
    private String apellidos;

    @Column(length = 20, nullable = false)
    private String direccion;

    @JsonIgnoreProperties(value = {"cliente", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public Cliente() {
        this.pedidos = new ArrayList<>();
    }

    private static final long serialVersionUID = 1L;
}
