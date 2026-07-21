package br.com.jeniferocha.quickcart.repository;

import br.com.jeniferocha.quickcart.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository <Pedido, Long>{
}
