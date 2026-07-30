package br.com.jeniferocha.quickcart.dto;

import br.com.jeniferocha.quickcart.model.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// response principal do pedido, exibindo id, data, status, valor total e a lista com todos os itens comprados
public record PedidoResponseDTO(Long id,
                                LocalDate dataCriacao,
                                BigDecimal valorTotal,
                                Status status,
                                List<ItemPedidoResponseDTO> itens) {
}
