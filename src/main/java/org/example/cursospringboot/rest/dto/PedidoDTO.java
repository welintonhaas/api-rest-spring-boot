package org.example.cursospringboot.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {

    private Long id;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;

}
