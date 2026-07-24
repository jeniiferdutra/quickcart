package br.com.jeniferocha.quickcart.controller;

import br.com.jeniferocha.quickcart.dto.ProdutoRequestDTO;
import br.com.jeniferocha.quickcart.dto.ProdutoResponseDTO;
import br.com.jeniferocha.quickcart.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
