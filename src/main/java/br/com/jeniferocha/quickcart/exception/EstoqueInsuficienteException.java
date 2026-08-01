package br.com.jeniferocha.quickcart.exception;

// quando a quantidade solicitada de um produto é maior que a disponível no estoque
public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
