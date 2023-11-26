package com.example.demo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long agencia;
    private Long digitoAgencia;
    private BigDecimal saldo;
    private String titular;
    private String cpf;
    private String TipoConta;

    public Conta() {
    }

    public Conta(Long id, Long agencia, Long digitoAgencia, BigDecimal saldo, String titular, String cpf, String TipoConta) {
        this.id = id;
        this.agencia = agencia;
        this.digitoAgencia = digitoAgencia;
        this.saldo = saldo;
        this.titular = titular;
        this.cpf = cpf;
        this.TipoConta = TipoConta;
    }

    public Long getId() {
        return id;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public Long getDigitoAgencia() {
        return digitoAgencia;
    }
    public void setDigitoAgencia(Long digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }


    public String getCpf() {
        return cpf;
    }


    public String getTipoConta() {
        return TipoConta;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(cpf, conta.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
