package com.example.demo.domain.usecase;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class Depositar {
    private ContaGateway contaGateway;

    public Depositar(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }
    public Conta execute(String cpf, Double valor) throws Exception {
        // 1- Buscar a conta pelo cpf
        // 2- Se nao existir, lancar uma exception
        // 3- Se existir, adicionar o valor ao saldo da conta
        // 4- Salvar a conta

        Conta conta = contaGateway.buscarPorCpf(cpf);
        if(conta == null) {
            throw new Exception("Conta não encontrada");
        }
        else{
            BigDecimal valorBig = new BigDecimal(valor);
            conta.setSaldo(conta.getSaldo().add(valorBig));
            return contaGateway.salvar(conta);
        }
    }
}
