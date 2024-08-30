package org.example.cursospringboot.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "CLIENTE")
public class Cliente
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOME",length = 100)
	private String nome;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;

	public Cliente(String nome)
	{
		this.nome = nome;
	}

	public Cliente(Integer id, String nome)
	{
		this.id = id;
		this.nome = nome;
	}

	public Cliente() {}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	@Override
	public String toString()
	{
		return "Cliente{" + "nome='" + nome + '\'' + ", id=" + id + '}';
	}

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
