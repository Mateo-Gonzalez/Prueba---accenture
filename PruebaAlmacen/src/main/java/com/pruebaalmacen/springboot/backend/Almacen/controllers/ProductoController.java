
package com.pruebaalmacen.springboot.backend.Almacen.controllers;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Producto;
import com.pruebaalmacen.springboot.backend.Almacen.services.IProductoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }

    @GetMapping
    public List<Producto> getAll(){
        List<Producto> productos = StreamSupport
        .stream(productoService.findAll().spliterator(), false)
        .collect(Collectors.toList());
        return productos;
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id")Long productoId){
        Optional<Producto> oProducto = productoService.findById(productoId);

		if (!oProducto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
				
		return ResponseEntity.ok(oProducto);
    }
}
