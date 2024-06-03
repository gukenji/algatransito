package com.algaworks.algatransito.api.controller;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.service.RegistroProprietarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final RegistroProprietarioService registroProprietarioService;
    private final ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar() {
//        return proprietarioRepository.findByNomeContaining("Te");
        return proprietarioRepository.findAll();
    }


    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId){

        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
//       Optional<Proprietario> proprietario = proprietarioRepository.findById(proprietarioId);
//       if(proprietario.isPresent()) {
//           return ResponseEntity.ok(proprietario.get());
//       }
//       return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario) {
        return registroProprietarioService.salvar(proprietario);
//        return proprietarioRepository.save(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
                                                  @Valid @RequestBody Proprietario proprietario) {
        if(!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }
        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
        if(!proprietarioRepository.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }
        registroProprietarioService.excluir(proprietarioId);
//        proprietarioRepository.deleteById(proprietarioId);
        return ResponseEntity.noContent().build();
    }

}
