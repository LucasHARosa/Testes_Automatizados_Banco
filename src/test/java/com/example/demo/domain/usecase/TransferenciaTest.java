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
public class TransferenciaTest {
    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private Transferencia transferencia;

    @Test
    public void deveTransferir() throws Exception{
        // Given
        Conta contaOrigem = new Conta(1L, 3L,3L, new BigDecimal(10.0), "Lucas", "222222222","Corrente");
        Conta contaDestino = new Conta(2L, 3L,1L, new BigDecimal(10.0), "Lucas", "123456789", "Corrente");

        // When
        when(contaGateway.buscarPorCpf(contaOrigem.getCpf())).thenReturn(contaOrigem);
        when(contaGateway.buscarPorCpf(contaDestino.getCpf())).thenReturn(contaDestino);
        when(contaGateway.salvar(contaOrigem)).thenReturn(contaOrigem);
        when(contaGateway.salvar(contaDestino)).thenReturn(contaDestino);

        Conta[] contas = transferencia.execute(contaOrigem.getCpf(), contaDestino.getCpf(), 10.0);

        // Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(0.0, contas[0].getSaldo().doubleValue()),
                () -> Assertions.assertEquals(20.0, contas[1].getSaldo().doubleValue())
        );

        verify(contaGateway, times(1)).buscarPorCpf(contaOrigem.getCpf());
        verify(contaGateway, times(1)).buscarPorCpf(contaDestino.getCpf());
        verify(contaGateway, times(1)).salvar(contaOrigem);
        verify(contaGateway, times(1)).salvar(contaDestino);

    }

    @Test
    public void deveLancarExceptionCasoAContaOrigemNaoExista() {
        // Given
        Conta contaOrigem = new Conta(1L, 3L,3L, new BigDecimal(10.0), "Lucas", "222222222","Corrente");
        Conta contaDestino = new Conta(2L, 3L,1L, new BigDecimal(10.0), "Lucas", "123456789", "Corrente");

        // When Then
        when(contaGateway.buscarPorCpf(contaOrigem.getCpf())).thenReturn(null);

        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> transferencia.execute(contaOrigem.getCpf(), contaDestino.getCpf(), 10.0)
        );

        Assertions.assertEquals("Conta origem não encontrada", throwable.getMessage());

        verify(contaGateway, times(1)).buscarPorCpf(contaOrigem.getCpf());
        verify(contaGateway, times(0)).buscarPorCpf(contaDestino.getCpf());
        verify(contaGateway, times(0)).salvar(any());
        verify(contaGateway, times(0)).salvar(any());
    }

    @Test
    public void deveLancarExceptionCasoAContaDestinoNaoExista() {
        // Given
        Conta contaOrigem = new Conta(1L, 3L,3L, new BigDecimal(10.0), "Lucas", "222222222","Corrente");
        Conta contaDestino = new Conta(2L, 3L,1L, new BigDecimal(10.0), "Lucas", "123456789", "Corrente");

        // When Then
        when(contaGateway.buscarPorCpf(contaOrigem.getCpf())).thenReturn(contaOrigem);
        when(contaGateway.buscarPorCpf(contaDestino.getCpf())).thenReturn(null);

        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> transferencia.execute(contaOrigem.getCpf(), contaDestino.getCpf(), 10.0)
        );

        Assertions.assertEquals("Conta destino não encontrada", throwable.getMessage());

        verify(contaGateway, times(1)).buscarPorCpf(contaOrigem.getCpf());
        verify(contaGateway, times(1)).buscarPorCpf(contaDestino.getCpf());
        verify(contaGateway, times(0)).salvar(any());
        verify(contaGateway, times(0)).salvar(any());
    }

    @Test
    public void deveLancarExceptionCasoSaldoInsuficiente() {
        // Given
        Conta contaOrigem = new Conta(1L, 3L,3L, new BigDecimal(0.0), "Lucas", "222222222","Corrente");
        Conta contaDestino = new Conta(2L, 3L,1L, new BigDecimal(10.0), "Lucas", "123456789", "Corrente");

        // When Then
        when(contaGateway.buscarPorCpf(contaOrigem.getCpf())).thenReturn(contaOrigem);
        when(contaGateway.buscarPorCpf(contaDestino.getCpf())).thenReturn(contaDestino);

        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> transferencia.execute(contaOrigem.getCpf(), contaDestino.getCpf(), 10.0)
        );

        Assertions.assertEquals("Saldo insuficiente", throwable.getMessage());

        verify(contaGateway, times(1)).buscarPorCpf(contaOrigem.getCpf());
        verify(contaGateway, times(1)).buscarPorCpf(contaDestino.getCpf());
        verify(contaGateway, times(0)).salvar(any());
        verify(contaGateway, times(0)).salvar(any());
    }


}
