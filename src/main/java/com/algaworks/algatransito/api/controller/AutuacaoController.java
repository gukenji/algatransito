package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.AutuacaoAssembler;
import com.algaworks.algatransito.api.model.AutuacaoRepresentationModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.service.RegistroAutuacaoService;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/veiculo/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final AutuacaoAssembler autuacaoAssembler;
    private final RegistroAutuacaoService registroAutuacaoService;
    private final RegistroVeiculoService registroVeiculoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoRepresentationModel registrar(@Valid @RequestBody AutuacaoInput autuacaoInput,
                                                 @PathVariable Long veiculoId) {
        Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada = registroAutuacaoService.registrar(veiculoId, novaAutuacao);
        return autuacaoAssembler.toModel(autuacaoRegistrada);


    }

    @GetMapping
    public List<AutuacaoRepresentationModel> listar(@PathVariable Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
        return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());

    }
}
