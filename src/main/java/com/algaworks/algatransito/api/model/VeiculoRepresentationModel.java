package com.algaworks.algatransito.api.model;

import com.algaworks.algatransito.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class VeiculoRepresentationModel {

    private Long id;
    private ProprietarioResumoRepresentationModel proprietario;
    private String marca;
    private String modelo;
    private String numeroPlaca;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastrada;
    private OffsetDateTime dataApreensao;

}
