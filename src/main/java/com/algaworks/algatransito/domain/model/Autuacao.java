package com.algaworks.algatransito.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Autuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @NotNull
    @Valid
    private Veiculo veiculo;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal valorMulta;

    @NotNull
    private OffsetDateTime dataOcorrencia;

}
