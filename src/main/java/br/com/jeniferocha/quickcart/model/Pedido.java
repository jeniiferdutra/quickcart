package br.com.jeniferocha.quickcart.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCriacao;

    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Pedido(){}

    public Pedido(Long id, LocalDate dataCriacao, BigDecimal valorTotal, Status status) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status situacao) {
        this.status = situacao;
    }
}
