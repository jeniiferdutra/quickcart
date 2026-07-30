package br.com.jeniferocha.quickcart.controller;

import br.com.jeniferocha.quickcart.dto.ProdutoRequestDTO;
import br.com.jeniferocha.quickcart.dto.ProdutoResponseDTO;
import br.com.jeniferocha.quickcart.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // recebe requisições e devolve json
@RequestMapping("/produtos") // http
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping // criar/salvar novos dados
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO produtoSalvo = produtoService.salvarProduto(dto); // salvar no banco
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo); // retorna status HTTP 201 (Created/Criado)
    }

    @GetMapping // get para buscar/listar dados do banco
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos); // Retorna HTTP 200 (OK)
    }

    @GetMapping("/{id}") // busca apenas o que já está salvo no banco de dados
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        ProdutoResponseDTO produto = produtoService.buscarPorId(id); // busca via Service
        return ResponseEntity.ok(produto); // retorna HTTP 200 (OK) com o json do produto
    }

    @PutMapping("/{id}") // atualiza os dados do item
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoRequestDTO dto) {

        ProdutoResponseDTO produtoAtualizado = produtoService.atualizarProduto(id, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build(); // http 204 -> área de resposta estará vazia
    }
}
