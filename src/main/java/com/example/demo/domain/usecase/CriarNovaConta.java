package com.example.demo.domain.usecase;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;
import org.springframework.stereotype.Component;

@Component
public class CriarNovaConta {
    private ContaGateway contaGateway;

    public CriarNovaConta(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }

    public Conta execute(Conta conta) throws Exception {
        if(contaGateway.buscarPorCpf(conta.getCpf()) != null) {
            throw new Exception("Usuario ja possui uma conta");
        }
        Conta novaConta = contaGateway.salvar(conta);

        return novaConta;
    }

}
