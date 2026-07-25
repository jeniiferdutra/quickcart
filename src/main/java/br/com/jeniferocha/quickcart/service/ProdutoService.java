package br.com.jeniferocha.quickcart.service;

import br.com.jeniferocha.quickcart.dto.ProdutoRequestDTO;
import br.com.jeniferocha.quickcart.dto.ProdutoResponseDTO;
import br.com.jeniferocha.quickcart.model.Produto;
import br.com.jeniferocha.quickcart.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponseDTO salvarProduto(ProdutoRequestDTO dto) {
        Produto produto = new Produto(); // converter o dto p/ a entidade produto
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());

        Produto produtoSalvo = produtoRepository.save(produto); // salvar no banco usando repository

        return new ProdutoResponseDTO( // converter o que salvou p/ o dto de resposta e retornar
                produtoSalvo.getId(),
                produtoSalvo.getNome(),
                produtoSalvo.getPreco(),
                produtoSalvo.getQuantidadeEstoque()
        );
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidadeEstoque()
                ))
                .toList();
    }
}
