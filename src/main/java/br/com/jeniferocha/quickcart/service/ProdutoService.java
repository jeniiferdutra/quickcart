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

    public ProdutoResponseDTO buscarPorId(Long id) { // caso o item não exista
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeEstoque()
        );
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id) // busca o item, se não achar, lança uma exceção
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        produto.setNome(dto.nome()); // att os dados do objeto com os novos valores
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());

        Produto produtoAtualizado = produtoRepository.save(produto); // salva no banco

        return new ProdutoResponseDTO( // dados convertidos em dto
                produtoAtualizado.getId(),
                produtoAtualizado.getNome(),
                produtoAtualizado.getPreco(),
                produtoAtualizado.getQuantidadeEstoque()
        );
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id) // se o item existe no banco antes de tentar deletar
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        produtoRepository.delete(produto); // apaga o registro do banco
    }
}
