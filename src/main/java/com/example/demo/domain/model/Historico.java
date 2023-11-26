package com.example.demo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String cpfContaOrigem;
    private String cpfContaDestino;
    private Double valor;
    private String data;
    private String tipoOperacao;

    public Historico() {
    }

    public Historico(Long id, String cpfContaOrigem, String cpfContaDestino, Double valor, String data, String tipoOperacao) {
        this.id = id;
        this.cpfContaOrigem = cpfContaOrigem;
        this.cpfContaDestino = cpfContaDestino;
        this.valor = valor;
        this.data = data;
        this.tipoOperacao = tipoOperacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfContaOrigem() {
        return cpfContaOrigem;
    }

    public void setCpfContaOrigem(String cpfContaOrigem) {
        this.cpfContaOrigem = cpfContaOrigem;
    }

    public String getCpfContaDestino() {
        return cpfContaDestino;
    }

    public void setCpfContaDestino(String cpfContaDestino) {
        this.cpfContaDestino = cpfContaDestino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historico historico = (Historico) o;
        return Objects.equals(id, historico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
