package br.com.organizador_financas.controller;

import br.com.organizador_financas.dto.request.MovimentacaoRequest;
import br.com.organizador_financas.entity.Movimentacao;
import br.com.organizador_financas.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> listarTodas() {
        return ResponseEntity.ok(movimentacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoService.buscarPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Movimentacao> criar(
            @Valid @RequestBody MovimentacaoRequest request) {

        return ResponseEntity.ok(movimentacaoService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> atualizar(
            @PathVariable Long id,
            @RequestBody Movimentacao movimentacao) {

        return ResponseEntity.ok(
                movimentacaoService.atualizar(id, movimentacao)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        movimentacaoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}