package br.com.organizador_financas.service;

import br.com.organizador_financas.entity.Categoria;
import br.com.organizador_financas.exception.CategoriaEmUsoException;
import br.com.organizador_financas.exception.CategoriaNotFoundException;
import br.com.organizador_financas.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import br.com.organizador_financas.repository.MovimentacaoRepository;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public CategoriaService(
            CategoriaRepository categoriaRepository,
            MovimentacaoRepository movimentacaoRepository) {

        this.categoriaRepository = categoriaRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria criar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoria) {
        Categoria categoriaExistente = buscarPorId(id);

        categoriaExistente.setNome(categoria.getNome());

        return categoriaRepository.save(categoriaExistente);
    }

    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);

        if (movimentacaoRepository.existsByCategoriaId(id)) {
            throw new CategoriaEmUsoException();
        }

        categoriaRepository.delete(categoria);
    }
}