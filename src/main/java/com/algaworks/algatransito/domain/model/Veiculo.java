package com.algaworks.algatransito.domain.model;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Veiculo {

    // Validação porque pode ser feito a insercao de dados também atraves do DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

//    @JoinColumn(name = "proprietario_id")
    // Não necessário pelos nomes serem iguais, neste caso.
    @ManyToOne
    @NotNull
    @ConvertGroup(from = Default.class, to = ValidationGroups.ProprietarioId.class)
    @Valid
    private Proprietario proprietario;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    // XXX0000
    // XXX0X00
    private String placa;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastrada;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy= "veiculo", cascade = CascadeType.ALL)
    //CascadeType.ALL = todas as operações devem ser propagadas em cascata
    // Todas mudancas na lista de autuacoes será sincronizada para o banco de dados
    private List<Autuacao> autuacoes = new ArrayList<>();


    // pode haver metodos de negocio aqui também, conforme abaixo
    public Autuacao adicionarAutuacao(Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }

    public void apreender() {
        if (estaApreendido()) {
            throw new NegocioException("Veículo já está apreendido");
        }
        setStatus(StatusVeiculo.APREENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }

    public boolean estaApreendido() {
        return StatusVeiculo.APREENDIDO.equals(getStatus());
    }

    public void removerApreensao() {
        if (!estaApreendido()) {
            throw new NegocioException("Veículo não está apreendido");
        }
        setStatus(StatusVeiculo.REGULAR);
        setDataCadastrada(OffsetDateTime.now());
    }
}
