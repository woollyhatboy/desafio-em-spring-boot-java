package com.project.picpaysimpli.controller;

import com.project.picpaysimpli.domain.user.dto.InformationToTransferDTO;
import com.project.picpaysimpli.domain.user.dto.InsertMoneyDTO;
import com.project.picpaysimpli.service.user.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping("/transferencia")
    @Transactional
    public ResponseEntity transferMoney(@RequestBody InformationToTransferDTO informationToTransferDTO) {
        return transferenciaService.transferMoney(informationToTransferDTO);
    }

    @PostMapping("/inserir")
    @Transactional
    public ResponseEntity insertMoney(@RequestBody InsertMoneyDTO insertMoneyDTO) {
        return transferenciaService.insertMoney(insertMoneyDTO);
    }

}
