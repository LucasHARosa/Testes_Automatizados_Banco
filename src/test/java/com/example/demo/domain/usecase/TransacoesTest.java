package com.example.demo.domain.usecase;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;
import com.example.demo.dummy.ContaGatewayDummyImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TransacoesTest {
    @Test
    public void deveLancarUmaExceptionCasoUsuarioNaoPossuaContaAoDepositar() throws Exception {
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        Transacoes transacoes = new Transacoes(contaGateway);
        Assertions.assertThrows(Exception.class, () -> {
            transacoes.depositar("123", 100.00);
        });

    }

    @Test
    public void deveDepositarValorNaConta() throws Exception {
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        Transacoes transacoes = new Transacoes(contaGateway);
        Conta conta = transacoes.depositar("12345678900", 100.00);

        Assertions.assertEquals(new BigDecimal("200.00"), conta.getSaldo());
    }

    @Test
    public void deveLancarUmaExceptionCasoUsuarioNaoPossuaContaAoSacar() throws Exception {
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        Transacoes transacoes = new Transacoes(contaGateway);
        Assertions.assertThrows(Exception.class, () -> {
            transacoes.sacar("123", 100.00);
        });

    }

    @Test
    public void deveLancarUmaExceptionCasooUsuarioNaoPossuaSaldoSuficiente() throws Exception {
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        Transacoes transacoes = new Transacoes(contaGateway);
        Assertions.assertThrows(Exception.class, () -> {
            transacoes.sacar("12345678900", 1000.00);
        });

    }

    @Test
    public void deveSacarNormalmente() throws Exception{
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        Transacoes transacoes = new Transacoes(contaGateway);

        Conta conta = transacoes.sacar("12345678900", 50.00);
        Assertions.assertEquals(new BigDecimal("50.00"), conta.getSaldo());
    }
}
