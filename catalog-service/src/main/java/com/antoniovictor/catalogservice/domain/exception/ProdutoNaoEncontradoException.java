package com.antoniovictor.catalogservice.domain.exception;

public class ProdutoNaoEncontradoException extends Exception {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
