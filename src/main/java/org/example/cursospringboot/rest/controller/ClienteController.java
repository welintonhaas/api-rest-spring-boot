package org.example.cursospringboot.rest.controller;

import org.example.cursospringboot.domain.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @RequestMapping(
            value = "/hello/{nome}",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nome, @RequestBody Cliente cliente) {
        return String.format("Olá, %s!", nome);
    }

}
