package br.com.jeniferocha.quickcart.controller;

import br.com.jeniferocha.quickcart.dto.PedidoRequestDTO;
import br.com.jeniferocha.quickcart.dto.PedidoResponseDTO;
import br.com.jeniferocha.quickcart.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping // criar/fechar um novo pedido
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO dto) {
        PedidoResponseDTO pedidoCriado = pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado); // HTTP 201 Created
    }

    @GetMapping("/{id}") // buscar um pedido existente pelo seu ID na URL
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        PedidoResponseDTO pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido); // HTTP 200 OK
    }
}
