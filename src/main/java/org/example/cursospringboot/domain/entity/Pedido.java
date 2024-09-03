package org.example.cursospringboot.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cursospringboot.domain.enums.StatusPedido;
import org.example.cursospringboot.validation.NotEmptyList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Column(name = "data_pedido")
	private LocalDate dataPedido;

	@Column(name = "total", precision = 20, scale = 2)
	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusPedido status;

	@NotEmptyList(message = "Pedido não pode ser realizado sem itens")
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens;

}
