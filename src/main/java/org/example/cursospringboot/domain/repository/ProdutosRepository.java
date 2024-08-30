package org.example.cursospringboot.domain.repository;

import org.example.cursospringboot.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}
