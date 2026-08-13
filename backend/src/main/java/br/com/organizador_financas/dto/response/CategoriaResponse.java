package br.com.organizador_financas.dto.response;

import br.com.organizador_financas.entity.Categoria;

public class CategoriaResponse {

    private Long id;
    private String nome;

    public CategoriaResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static CategoriaResponse fromEntity(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome()
        );
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}