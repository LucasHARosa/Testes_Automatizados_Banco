package com.example.demo.domain.gateway;

import com.example.demo.domain.model.Conta;

public interface ContaGateway {
    Conta buscarPorCpf(String cpf);
    Conta salvar(Conta conta);

}
