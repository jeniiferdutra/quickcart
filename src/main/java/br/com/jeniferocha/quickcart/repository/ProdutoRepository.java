package br.com.jeniferocha.quickcart.repository;

import br.com.jeniferocha.quickcart.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
}
