package br.com.organizador_financas.repository;

import br.com.organizador_financas.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}