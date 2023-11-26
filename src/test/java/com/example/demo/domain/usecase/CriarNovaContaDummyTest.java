package com.example.demo.domain.usecase;


        import com.example.demo.domain.gateway.ContaGateway;
        import com.example.demo.domain.model.Conta;
        import com.example.demo.dummy.ContaGatewayDummyImpl;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;

        import java.math.BigDecimal;

public class CriarNovaContaDummyTest {
    @Test
    public void deveLancarExceptionCasoAContaJaExista() {
        // Given
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        CriarNovaConta criarNovaConta = new CriarNovaConta(contaGateway);
        Conta conta =
                new Conta(1L, 2L, 3L, BigDecimal.ZERO, "Pedro", "123456789","Corrente");

        // When Then

        Throwable throwable = Assertions.assertThrows(
                Exception.class,
                () -> criarNovaConta.execute(conta)
        );

        Assertions.assertEquals("Usuario ja possui uma conta", throwable.getMessage());
    }

    @Test
    public void deveCriarNovaConta() throws Exception {
        // Given
        ContaGateway contaGateway = new ContaGatewayDummyImpl();
        CriarNovaConta criarNovaConta = new CriarNovaConta(contaGateway);
        Conta conta =
                new Conta(1L, 2L, 3L, BigDecimal.ZERO, "Pedro", "222222222","Corrente");

        // When
        Conta novaConta = criarNovaConta.execute(conta);

        // Then
        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, novaConta.getId()),
                () -> Assertions.assertEquals("Pedro", novaConta.getTitular())
        );
    }
}