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
public class CriarNovaContaTest {

    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private CriarNovaConta criarNovaConta;

    @Test
    public void deveCriarNovaConta() throws Exception {
        // Given
        Conta conta =
                new Conta(1L, 3L,3L, BigDecimal.ZERO, "Lucas", "222222222","Corrente");

        // When
        // Mocks response
        when(contaGateway.buscarPorCpf(conta.getCpf())).thenReturn(null); // stub
        when(contaGateway.salvar(any())).thenReturn(conta);


        Conta novaConta = criarNovaConta.execute(conta);

        // Then
        Assertions.assertAll(
                () -> Assertions.assertEquals("Lucas", novaConta.getTitular()),
                () -> Assertions.assertEquals("222222222", novaConta.getCpf()),
                () -> Assertions.assertEquals("Corrente", novaConta.getTipoConta()),
                () -> Assertions.assertEquals(3L, novaConta.getAgencia()),
                () -> Assertions.assertEquals(3L, novaConta.getDigitoAgencia()),
                () -> Assertions.assertEquals(BigDecimal.ZERO, novaConta.getSaldo())

        );

        verify(contaGateway, times(1)).buscarPorCpf(conta.getCpf());
        verify(contaGateway, times(1)).salvar(any());
    }
    @Test
    public void deveLancarExceptionCasoAContaJaExista() {
        // Given
        Conta conta =
                new Conta(2L, 3L,1L, BigDecimal.ZERO, "Lucas", "123456789", "Corrente");

        // When Then
        when(contaGateway.buscarPorCpf(conta.getCpf())).thenReturn(conta);

        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> criarNovaConta.execute(conta)
        );

        Assertions.assertEquals("Usuario ja possui uma conta", throwable.getMessage());

        verify(contaGateway, times(1)).buscarPorCpf(conta.getCpf());
        verify(contaGateway, never()).salvar(conta);
    }
}

