package br.com.jeniferocha.quickcart.service;

import br.com.jeniferocha.quickcart.dto.*;
import br.com.jeniferocha.quickcart.exception.EstoqueInsuficienteException;
import br.com.jeniferocha.quickcart.exception.ResourceNotFoundException;
import br.com.jeniferocha.quickcart.model.ItemPedido;
import br.com.jeniferocha.quickcart.model.Pedido;
import br.com.jeniferocha.quickcart.model.Produto;
import br.com.jeniferocha.quickcart.model.Status;
import br.com.jeniferocha.quickcart.repository.PedidoRepository;
import br.com.jeniferocha.quickcart.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional // vai garantir q tudo seja salvo ou nada seja salvo se der erro
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDate.now());
        pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);

        BigDecimal valorTotal = BigDecimal.ZERO;
        List<ItemPedidoResponseDTO> itensResponse = new ArrayList<>();

        // processa cada item enviado na requisição
        for (ItemPedidoRequestDTO itemDTO : dto.itens()) {
            // buscar produto
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + itemDTO.produtoId()));

            // se tem estoque suficiente
            if (produto.getQuantidadeEstoque() < itemDTO.quantidade()) {
                throw new EstoqueInsuficienteException(
                        "Estoque insuficiente para o produto '" + produto.getNome() +
                                "'. Disponível: " + produto.getQuantidadeEstoque() +
                                ", Solicitado: " + itemDTO.quantidade()
                );
            }

            // da baixa no estoque
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemDTO.quantidade());
            produtoRepository.save(produto);

            // cria a entidade ItemPedido com o preço do momento
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDTO.quantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());

            // associa o item ao pedido
            pedido.adicionarItem(itemPedido);

            // faz o calculo de subtotal do item e soma ao valor total do pedido
            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.quantidade()));
            valorTotal = valorTotal.add(subtotal);

            // dto de resposta para este item
            itensResponse.add(new ItemPedidoResponseDTO(
                    produto.getId(),
                    produto.getNome(),
                    itemDTO.quantidade(),
                    produto.getPreco(),
                    subtotal
            ));
        }

        pedido.setValorTotal(valorTotal);

        // salva o pedido e os itens no banco
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // retorna o dto completo de response
        return new PedidoResponseDTO(
                pedidoSalvo.getId(),
                pedidoSalvo.getDataCriacao(),
                pedidoSalvo.getValorTotal(),
                pedidoSalvo.getStatus(),
                itensResponse
        );
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o ID: " + id));

        List<ItemPedidoResponseDTO> itensDTO = pedido.getItens().stream()
                .map(item -> new ItemPedidoResponseDTO(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
                ))
                .toList();

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataCriacao(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                itensDTO
        );
    }
}
