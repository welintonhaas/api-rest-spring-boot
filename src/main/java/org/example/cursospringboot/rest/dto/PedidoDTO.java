package org.example.cursospringboot.rest.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Informe o código do cliente")
    private Integer cliente;

    @NotNull(message = "Campo total do Pedido é obrigatório")
    private BigDecimal total;

    private List<ItemPedidoDTO> itens;

}
