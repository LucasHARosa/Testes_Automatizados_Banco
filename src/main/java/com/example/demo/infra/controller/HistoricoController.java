package com.example.demo.infra.controller;

import com.example.demo.domain.model.Conta;
import com.example.demo.domain.model.Historico;
import com.example.demo.domain.usecase.Depositar;
import com.example.demo.domain.usecase.Sacar;
import com.example.demo.domain.usecase.Transferencia;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {
    private Depositar depositar;
    private Sacar sacar;
    private Transferencia transferencia;


    public HistoricoController(Depositar depositar, Sacar sacar, Transferencia transferencia) {

        this.depositar = depositar;
        this.sacar = sacar;
        this.transferencia = transferencia;
    }

    @PostMapping("/depositar")
    public ResponseEntity depositar(@RequestBody Historico historico) throws Exception {
        Conta conta;
        try{
            conta = depositar.execute(historico.getCpfContaDestino(),historico.getValor());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PostMapping("/sacar")
    public ResponseEntity sacar(@RequestBody Historico historico) throws Exception {
        Conta conta;
        try{
            conta = sacar.execute(historico.getCpfContaOrigem(), historico.getValor());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PostMapping("/transferir")
    public ResponseEntity transferir(@RequestBody Historico historico) throws Exception {
        Conta[] conta;
        try{
            conta = transferencia.execute(historico.getCpfContaOrigem(), historico.getCpfContaDestino(), historico.getValor());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }



}
