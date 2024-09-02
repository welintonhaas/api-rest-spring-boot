package org.example.cursospringboot.service;

import org.example.cursospringboot.domain.entity.Pedido;
import org.example.cursospringboot.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
