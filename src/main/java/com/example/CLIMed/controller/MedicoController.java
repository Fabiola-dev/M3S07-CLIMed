package com.example.CLIMed.controller;

import com.example.CLIMed.dtos.MedicoDTO;
import com.example.CLIMed.model.MedicoModel;
import com.example.CLIMed.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController("/")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(MedicoController.class);

    @PostMapping("medico")
    public ResponseEntity<MedicoModel> saveMedico(
            @RequestBody @Valid MedicoDTO medicoDTO) {
        var medicoModel = new MedicoModel();

        BeanUtils.copyProperties(medicoDTO,medicoModel);
        LOGGER.info("Instância médico cadastrada" + medicoDTO.nome());
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoRepository.save(medicoModel));
    }

    @GetMapping("medicos")
    public ResponseEntity<List<MedicoModel>> getAllMedicos(){
        LOGGER.info("Listagem médico concluída.");
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAll());
    }

    @GetMapping("medicos/{id}")
    public ResponseEntity<Object> getOneMedico(@PathVariable UUID id){
        Optional<MedicoModel> medicoO = medicoRepository.findById(id);

        if (medicoO.isEmpty()){
            LOGGER.info("Médico não foi identificado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado");
        }

        Optional<MedicoDTO> medicoDTO = medicoO.map(MedicoDTO::new);
        LOGGER.info("Consulta do médico concluída");
        return ResponseEntity.status(HttpStatus.OK).body(medicoDTO.get());
    }

    @PutMapping("medicos/{id}")
    public ResponseEntity<Object> updateMedico(
            @PathVariable UUID id,
            @RequestBody @Valid MedicoDTO medicoDTO){

        Optional<MedicoModel> medicoO = medicoRepository.findById(id);
        if (medicoO.isEmpty()) {
            LOGGER.info("ID [" + id + "] não encontrado na base de dados.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado");
        }
        var medicoModel = medicoO.get();
        BeanUtils.copyProperties(medicoDTO, medicoModel);
        LOGGER.info("Usuário de ID [" + id + "] atualizado na base de dados.");
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.save(medicoModel));
    }

    @DeleteMapping("medicos/{id}")
    public ResponseEntity<Object> excluirMedico(@PathVariable UUID id){
        Optional<MedicoModel> medicoO = medicoRepository.findById(id);
        if (medicoO.isEmpty()){
            LOGGER.info("ID [" + id + "] não identificado na base de dados.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico não encontrado");
        }

        medicoRepository.deleteById(id);
        LOGGER.info("Usuário de ID [" + id + "] excluído da base de dados.");
        return ResponseEntity.status(HttpStatus.OK).body("Médico excluído com sucesso.");
    }
}
