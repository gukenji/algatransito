package com.algaworks.algatransito.api.assembler;

import com.algaworks.algatransito.api.model.VeiculoRepresentationModel;
import com.algaworks.algatransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VeiculoAssembler {

    private final ModelMapper modelMapper;

    public VeiculoRepresentationModel toModel(Veiculo veiculo) {
        return modelMapper.map(veiculo,VeiculoRepresentationModel.class);
    }

    public List<VeiculoRepresentationModel> toCollectionModel(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(this::toModel)
                .toList();

    }

}
