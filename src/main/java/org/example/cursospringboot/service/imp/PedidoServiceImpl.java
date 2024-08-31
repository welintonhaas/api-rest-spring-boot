package org.example.cursospringboot.service.imp;

import org.example.cursospringboot.domain.repository.PedidosRepository;
import org.example.cursospringboot.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository repository;

    public PedidoServiceImpl(PedidosRepository repository) {
        this.repository = repository;
    }
}
