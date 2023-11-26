package com.example.demo.dummy;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;

import java.math.BigDecimal;

public class ContaGatewayDummyImpl implements ContaGateway {
    @Override
    public Conta buscarPorCpf(String cpf) {
        Conta conta = new Conta(1L, 2L, 3L,BigDecimal.ZERO , "Pedro", "123456789", "Corrente");
        if(conta.getCpf().equals(cpf)) {
            return conta;
        }
        return null;
    }

    @Override
    public Conta salvar(Conta conta) {

        return conta;
    }


}
