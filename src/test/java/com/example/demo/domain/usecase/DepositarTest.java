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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class DepositarTest {

    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private Depositar depositar;

    @Test
    public void deveDepositar() throws Exception {
        // Given
        Conta conta =
                new Conta(1L, 3L,3L, new BigDecimal(0.0), "Lucas", "222222222","Corrente");

        // When
        when(contaGateway.buscarPorCpf(conta.getCpf())).thenReturn(conta);
        when(contaGateway.salvar(any())).thenReturn(conta);

        Conta novaConta = depositar.execute(conta.getCpf(), 10.0);
        // Then

        Assertions.assertEquals(10.0, novaConta.getSaldo().doubleValue());

        verify(contaGateway, times(1)).buscarPorCpf(conta.getCpf());
        verify(contaGateway, times(1)).salvar(any());
    }

    @Test
    public void deveLancarExceptionCasoAContaNaoExista() {
        // Given
        Conta conta =
                new Conta(2L, 3L,1L, new BigDecimal(0.0), "Lucas", "123456789", "Corrente");

        // When Then
        when(contaGateway.buscarPorCpf(conta.getCpf())).thenReturn(null);

        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> depositar.execute(conta.getCpf(), 10.0)
        );

        Assertions.assertEquals("Conta não encontrada", throwable.getMessage());

        verify(contaGateway, times(1)).buscarPorCpf(conta.getCpf());
        verify(contaGateway, times(0)).salvar(any());
    }
}
