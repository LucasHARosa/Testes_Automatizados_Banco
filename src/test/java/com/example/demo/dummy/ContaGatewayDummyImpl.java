package com.example.demo.dummy;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;

public class ContaGatewayDummyImpl implements ContaGateway {
    @Override
    public Conta buscarPorCpf(String cpf) {
        Conta conta = new Conta(1L, 1L, 1L, null, "João", "12345678900");
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
