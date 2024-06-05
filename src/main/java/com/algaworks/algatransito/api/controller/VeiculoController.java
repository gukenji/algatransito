package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.algatransito.api.model.VeiculoRepresentationModel;
import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import com.algaworks.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;
    private final VeiculoAssembler veiculoAssembler;

    @GetMapping
    public List<VeiculoRepresentationModel> listar() {
        return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoRepresentationModel> buscar(@PathVariable Long veiculoId){

        return veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toModel
//                {
//                    var veiculoRepresentationModel = new VeiculoRepresentationModel();
//                    veiculoRepresentationModel.setId(veiculo.getId());
//                    veiculoRepresentationModel.setNomeProprietario(veiculo.getProprietario().getNome());
//                    veiculoRepresentationModel.setMarca(veiculo.getMarca());
//                    veiculoRepresentationModel.setModelo(veiculo.getModelo());
//                    veiculoRepresentationModel.setNumeroPlaca(veiculo.getPlaca());
//                    veiculoRepresentationModel.setStatus(veiculo.getStatus());
//                    veiculoRepresentationModel.setDataCadastro(veiculo.getDataCadastrada());
//                    veiculoRepresentationModel.setDataApreendido(veiculo.getDataApreensao());
//                    return veiculoRepresentationModel;
//                }
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoRepresentationModel cadastrar(@Valid @RequestBody Veiculo veiculo) {
        return veiculoAssembler.toModel(registroVeiculoService.cadastrar(veiculo));
    }



}
