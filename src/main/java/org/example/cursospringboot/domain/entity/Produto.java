package org.example.cursospringboot.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty(message = "Campo descrição é obrigatória")
	@Column(name = "descricao")
	private String descricao;

	@NotNull(message = "Campo preço é obrigatório")
	@Column(name = "preco_unitario")
	private BigDecimal preco;

}
