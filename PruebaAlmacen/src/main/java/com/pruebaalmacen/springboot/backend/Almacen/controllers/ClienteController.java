package com.pruebaalmacen.springboot.backend.Almacen.controllers;

import com.pruebaalmacen.springboot.backend.Almacen.entitys.Cliente;
import com.pruebaalmacen.springboot.backend.Almacen.services.IClienteService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Cliente usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(usuario));
    }

    @GetMapping
    public List<Cliente> getAll() {
        List<Cliente> usuario = StreamSupport
                .stream(clienteService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return usuario;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Long usuarioID) {

        Optional<Cliente> oUsuario = clienteService.findById(usuarioID);

        if (!oUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(oUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody Cliente usuarioUpdate, @PathVariable(value = "id") Long usuarioId) {
        Optional<Cliente> oUsuario = clienteService.findById(usuarioId);

        if (!oUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oUsuario.get().setDocumento(usuarioUpdate.getDocumento());
        oUsuario.get().setNombres(usuarioUpdate.getNombres());
        oUsuario.get().setApellidos(usuarioUpdate.getApellidos());
        oUsuario.get().setDireccion(usuarioUpdate.getDireccion());

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(oUsuario.get()));
    }
}
