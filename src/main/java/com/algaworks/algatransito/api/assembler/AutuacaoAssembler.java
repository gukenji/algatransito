package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.AutuacaoRepresentationModel;
import com.algaworks.algatransito.api.model.VeiculoRepresentationModel;
import com.algaworks.algatransito.api.model.input.AutuacaoInput;
import com.algaworks.algatransito.api.model.input.VeiculoInput;
import com.algaworks.algatransito.domain.model.Autuacao;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AutuacaoAssembler {

    private final ModelMapper modelMapper;

    public AutuacaoRepresentationModel toModel(Autuacao autuacao) {
        return modelMapper.map(autuacao, AutuacaoRepresentationModel.class);
    }

    public List<AutuacaoRepresentationModel> toCollectionModel(List<Autuacao> autuacoes) {
        return autuacoes.stream()
                .map(this::toModel)
                .toList();
    }

    public Autuacao toEntity(AutuacaoInput autuacaoInput) {
        return modelMapper.map(autuacaoInput, Autuacao.class);
    }

}
