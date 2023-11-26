package com.example.demo.domain.usecase;

import com.example.demo.domain.gateway.ContaGateway;
import com.example.demo.domain.model.Conta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class SacarTest {

    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private Sacar sacar;

    @Test
    public void deveSacar() throws Exception {
        // Given
        Conta conta =
                new Conta(1L, 3L,3L, new BigDecimal(10.0), "Lucas", "222222222","Corrente");

        // When
        when(contaGateway.buscarPorCpf(conta.getCpf())).thenReturn(conta);
        when(contaGateway.salvar(any())).thenReturn(conta);

        Conta novaConta = sacar.execute(conta.getCpf(), 10.0);
        // Then

        Assertions.assertEquals(0, novaConta.getSaldo().doubleValue());

        verify(contaGateway, times(1)).buscarPorCpf(conta.getCpf());
        verify(contaGateway, times(1)).salvar(any());
    }

    @Test
    public void deveLancarExceptionCasoAContaNaoExista() {
        // Given
        Conta conta =
                new Conta(2L, 3L,1L, new BigDecimal(10.0), "Lucas", "123456789", "Corrente");

        // When Then
        when(contaGateway.buscarPorCpf(conta.getCpf())).thenReturn(null);

        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> sacar.execute(conta.getCpf(), 10.0)
        );

        Assertions.assertEquals("Conta não encontrada", throwable.getMessage());

        verify(contaGateway, times(1)).buscarPorCpf(conta.getCpf());
        verify(contaGateway, times(0)).salvar(any());
    }

    @Test
    public void deveLancarExceptionCasoSaldoInsuficiente() {
        // Given
        Conta conta =
                new Conta(2L, 3L,1L, new BigDecimal(0.0), "Lucas", "123456789", "Corrente");

        // When Then
        when(contaGateway.buscarPorCpf(conta.getCpf())).thenReturn(conta);


        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> sacar.execute(conta.getCpf(), 10.0)
        );

        Assertions.assertEquals("Saldo insuficiente", throwable.getMessage());

        verify(contaGateway, times(1)).buscarPorCpf(conta.getCpf());
        verify(contaGateway, times(0)).salvar(any());
    }
}
