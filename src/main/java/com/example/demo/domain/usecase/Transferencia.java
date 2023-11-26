package com.example.demo.domain.usecase;


import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class Transferencia {

    private ContaGateway contaGateway;

    public Transferencia(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta[] execute(String cpfContaOrigem, String cpfContaDestino, Double valor) throws Exception {
        Conta contaOrigem = contaGateway.buscarPorCpf(cpfContaOrigem);
        if(contaOrigem == null ) {
            throw new Exception("Conta origem não encontrada");
        }
        Conta contaDestino = contaGateway.buscarPorCpf(cpfContaDestino);
        if(contaDestino == null){
            throw new Exception("Conta destino não encontrada");
        }
        BigDecimal valorBig = new BigDecimal(valor);
        if(contaOrigem.getSaldo().compareTo(valorBig) < 0){
            throw new Exception("Saldo insuficiente");
        }
        else{
            contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valorBig));
            contaDestino.setSaldo(contaDestino.getSaldo().add(valorBig));
            return new Conta[]{contaGateway.salvar(contaOrigem), contaGateway.salvar(contaDestino)};
        }
    }
}
