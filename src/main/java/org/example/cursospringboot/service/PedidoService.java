package org.example.cursospringboot.service;

import org.example.cursospringboot.domain.entity.Pedido;
import org.example.cursospringboot.domain.enums.StatusPedido;
import org.example.cursospringboot.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarStatus(Integer id, StatusPedido status);
}
