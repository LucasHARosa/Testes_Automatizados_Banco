package com.example.demo.domain.usecase;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;
import com.example.demo.dummy.ContaGatewayDummyImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CriarNovaContaTest {
    @Test
    public void deveLançarUmaExceptionCasoUsuarioJaPossuaConta() throws Exception {
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        CriarNovaConta criarNovaConta = new CriarNovaConta(contaGateway);

        Conta conta = new Conta(1L, 1L, 1L, null, "João", "12345678900");

        Assertions.assertThrows(Exception.class, () -> {
            criarNovaConta.execute(conta);
        });

    }

    @Test
    public void deveCriarUmaNovaConta() throws Exception {
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        CriarNovaConta criarNovaConta = new CriarNovaConta(contaGateway);

        Conta conta = new Conta(null, 1L, 1L, null, "João", "123");

        Conta novaConta = criarNovaConta.execute(conta);

        Assertions.assertAll(
                ()->Assertions.assertEquals(novaConta.getAgencia(), conta.getAgencia()),
                ()->Assertions.assertEquals(novaConta.getDigitoAgencia(), conta.getDigitoAgencia()),
                ()->Assertions.assertEquals(novaConta.getTitular(), conta.getTitular()),
                ()->Assertions.assertEquals(novaConta.getCpf(), conta.getCpf())
        );
    }
}
