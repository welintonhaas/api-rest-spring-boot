package org.example.cursospringboot.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Produto
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "preco_unitario")
	private BigDecimal preco;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public BigDecimal getPreco()
	{
		return preco;
	}

	public void setPreco(BigDecimal preco)
	{
		this.preco = preco;
	}
}
