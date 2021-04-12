package com.pruebaalmacen.springboot.backend.Almacen.entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Envios")
public class Envio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;
    
    @Column(name= "cantidadProducto", length =8,nullable = false )
    private Integer cantidadProducto;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    public Double valorEnvio() {
        return cantidadProducto.doubleValue() * producto.getPrecio();
    }

    private static final long serialVersionUID = 2L;
}
