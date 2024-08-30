package org.example.cursospringboot.repository;

import org.example.cursospringboot.domain.entity.Cliente;
import org.example.cursospringboot.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
