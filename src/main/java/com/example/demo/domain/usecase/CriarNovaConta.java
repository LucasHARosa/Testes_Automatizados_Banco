package com.example.demo.domain.usecase;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;

public class CriarNovaConta {
    private ContaGateway contaGateway;

    public CriarNovaConta(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta execute(Conta conta) throws Exception {
        // validar se o usuario ja possui uma conta
        if(contaGateway.buscarPorCpf(conta.getCpf()) != null) {
            // - se possuir vamos lancar uma exception
            throw new Exception("Usuario ja possui uma conta");
        }

        // criar uma nova conta
        Conta novaConta = contaGateway.salvar(conta);

        //return Conta;
        return novaConta;
    }
}
