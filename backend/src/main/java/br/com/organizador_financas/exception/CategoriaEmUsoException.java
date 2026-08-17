package br.com.organizador_financas.exception;

public class CategoriaEmUsoException extends RuntimeException {

    public CategoriaEmUsoException() {
        super("Não é possível excluir a categoria porque existem movimentações associadas a ela.");
    }
}