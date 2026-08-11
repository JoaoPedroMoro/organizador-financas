package br.com.organizador_financas.exception;

public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException(Long id) {
        super("Categoria não encontrada com o ID: " + id);
    }
}