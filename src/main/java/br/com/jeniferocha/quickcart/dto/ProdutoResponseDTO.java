package br.com.jeniferocha.quickcart.dto;

import java.math.BigDecimal;

public record ProdutoResponseDTO( // representa os dados que a sua API vai devolver para quem chama
        Long id,
        String nome,
        BigDecimal preco,
        Integer quantidadeEstoque
) {}
