package org.example.cursospringboot.rest.controller;

import org.example.cursospringboot.domain.entity.Cliente;
import org.example.cursospringboot.domain.repository.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClientesRepository clientes;

    private ClienteController(ClientesRepository clientesRepository) {
        this.clientes = clientesRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        cliente = clientes.save(cliente);
        return ResponseEntity.ok(cliente);
    }

}
