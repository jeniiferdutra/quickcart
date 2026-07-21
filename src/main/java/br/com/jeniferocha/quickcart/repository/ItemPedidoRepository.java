package br.com.jeniferocha.quickcart.repository;

import br.com.jeniferocha.quickcart.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
