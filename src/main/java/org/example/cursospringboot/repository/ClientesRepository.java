package org.example.cursospringboot.repository;

import java.util.List;
import org.example.cursospringboot.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientesRepository extends JpaRepository<Cliente, Integer>
{

	List<Cliente> findByNomeLike(String nome);

	List<Cliente> findByNomeOrIdOrderByIdDesc(String nome, Integer id);

	Cliente findOneByNome(String nome);

	boolean existsByNome(String nome);

	@Query(value = "SELECT * FROM Cliente c WHERE c.nome LIKE ':nome'", nativeQuery = true)
	//@Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);

	@Query("DELETE from Cliente c WHERE c.nome = :nome")
	@Modifying
	void deleteByNome(String nome);

	@Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE c.id = :id")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
