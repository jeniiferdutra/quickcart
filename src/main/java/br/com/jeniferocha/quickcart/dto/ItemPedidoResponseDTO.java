package br.com.jeniferocha.quickcart.dto;

import java.math.BigDecimal;

// response que detalha um item do pedido, exibindo nome do produto, quant, preço unitário e o subtotal
public record ItemPedidoResponseDTO(Long produtoId,
                                    String nomeProduto,
                                    Integer quantidade,
                                    BigDecimal precoUnitario,
                                    BigDecimal subtotal) {
}
