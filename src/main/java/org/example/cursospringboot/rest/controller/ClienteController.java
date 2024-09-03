package org.example.cursospringboot.rest.controller;

import jakarta.validation.Valid;
import org.example.cursospringboot.domain.entity.Cliente;
import org.example.cursospringboot.domain.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClientesRepository clientes;

    private ClienteController(ClientesRepository clientesRepository) {
        this.clientes = clientesRepository;
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clientes.findById(id)
            .orElseThrow( () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cliente Não Encontrado")
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {
        return clientes.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientes.findById(id)
            .map( cliente -> {
                clientes.delete(cliente);
                return cliente;
            }).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Cliente Não Encontrado")
            );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
        clientes.findById(id)
            .map( clienteExistente -> {
                cliente.setId(clienteExistente.getId());
                clientes.save(cliente);
                return clienteExistente;
            }).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Cliente Não Encontrado")
            );
    }

    @GetMapping
    @ResponseBody
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Cliente> example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }

}
