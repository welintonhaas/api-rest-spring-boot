package org.example.cursospringboot.rest.controller;

import jakarta.validation.Valid;
import org.example.cursospringboot.domain.entity.Produto;
import org.example.cursospringboot.domain.repository.ProdutosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutosRepository produtos;

    private ProdutoController(ProdutosRepository produtosRepository) {
        this.produtos = produtosRepository;
    }

    @GetMapping
    public List<Produto> find( Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Produto> example = Example.of(filtro, matcher);
        return produtos.findAll(example);
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Integer id) {
        return produtos.findById(id)
            .orElseThrow( () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Produto Não Encontrado")
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto) {
        return produtos.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        produtos.findById(id)
            .map( produto -> {
                produtos.delete(produto);
                return produto;
            }).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Produto Não Encontrado")
            );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
        produtos.findById(id)
            .map( produtoExistente -> {
                produto.setId(produtoExistente.getId());
                produtos.save(produto);
                return produtoExistente;
            }).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Produto Não Encontrado")
            );
    }


}
