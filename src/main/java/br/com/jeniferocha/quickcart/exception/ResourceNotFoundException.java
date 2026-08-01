package br.com.jeniferocha.quickcart.exception;

// quando um registro não for encontrado no banco de dados (retorna HTTP 404)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}
