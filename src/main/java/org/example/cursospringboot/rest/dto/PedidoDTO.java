package org.example.cursospringboot.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO
{
    private Long id;

    private Integer cliente;

    private BigDecimal total;

    private List<ItemPedidoDTO> itens;

}
