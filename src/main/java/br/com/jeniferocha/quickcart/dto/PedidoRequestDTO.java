package br.com.jeniferocha.quickcart.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PedidoRequestDTO(// representa a requisição pra criar um novo pedido contendo a lista de itens desejados
        @NotEmpty(message = "O pedido deve conter pelo menos um item")
        @Valid
        List<ItemPedidoRequestDTO> itens) {
}
