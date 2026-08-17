package br.com.organizador_financas.service;

import br.com.organizador_financas.dto.request.MovimentacaoRequest;
import br.com.organizador_financas.entity.Categoria;
import br.com.organizador_financas.entity.Movimentacao;
import br.com.organizador_financas.exception.CategoriaNotFoundException;
import br.com.organizador_financas.repository.MovimentacaoRepository;
import br.com.organizador_financas.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final CategoriaRepository categoriaRepository;

    public MovimentacaoService(
            MovimentacaoRepository movimentacaoRepository,
            CategoriaRepository categoriaRepository) {

        this.movimentacaoRepository = movimentacaoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }

    public Movimentacao buscarPorId(Long id) {
        return movimentacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Movimentação não encontrada com o ID: " + id));
    }

    public Movimentacao criar(MovimentacaoRequest request) {

        Categoria categoria = categoriaRepository
                .findById(request.getCategoriaId())
                .orElseThrow(() ->
                        new CategoriaNotFoundException(
                                request.getCategoriaId()
                        )
                );

        Movimentacao movimentacao = new Movimentacao(
                request.getDescricao(),
                request.getValor(),
                request.getData(),
                request.getTipo(),
                categoria
        );

        return movimentacaoRepository.save(movimentacao);
    }
    public Movimentacao atualizar(Long id, Movimentacao movimentacao) {

        Movimentacao movimentacaoExistente = buscarPorId(id);

        movimentacaoExistente.setDescricao(movimentacao.getDescricao());
        movimentacaoExistente.setValor(movimentacao.getValor());
        movimentacaoExistente.setData(movimentacao.getData());
        movimentacaoExistente.setTipo(movimentacao.getTipo());
        movimentacaoExistente.setCategoria(movimentacao.getCategoria());

        return movimentacaoRepository.save(movimentacaoExistente);
    }

    public void deletar(Long id) {
        Movimentacao movimentacao = buscarPorId(id);
        movimentacaoRepository.delete(movimentacao);
    }
}