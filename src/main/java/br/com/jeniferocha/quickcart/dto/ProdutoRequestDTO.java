package br.com.jeniferocha.quickcart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProdutoRequestDTO(  // representa os dados que chegam quando alguem tenta cadastrar um produto novo
    @NotBlank String nome,
    @NotNull @Positive BigDecimal preco,
    @NotNull @PositiveOrZero Integer quantidadeEstoque
) {}
