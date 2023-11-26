package com.example.demo.domain.usecase;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class Sacar {
    private ContaGateway contaGateway;

    public Sacar(ContaGateway contaGateway) {
        this.contaGateway = contaGateway;
    }
    public Conta execute(String cpf, Double valor) throws Exception {
        // buscar a conta pelo cpf
        // se nao existir, lancar uma exception
        // se existir, adicionar o valor ao saldo da conta
        // salvar a conta

        Conta conta = contaGateway.buscarPorCpf(cpf);
        if(conta == null) {
            throw new Exception("Conta não encontrada");
        }
        else{
            BigDecimal valorBig = new BigDecimal(valor);
            if(conta.getSaldo().compareTo(valorBig) < 0){
                throw new Exception("Saldo insuficiente");
            }
            else {
                conta.setSaldo(conta.getSaldo().subtract(valorBig));
                return contaGateway.salvar(conta);
            }
        }
    }
}
