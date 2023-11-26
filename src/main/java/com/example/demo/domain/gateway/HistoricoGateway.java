package com.example.demo.domain.gateway;

import com.example.demo.domain.model.Historico;

import java.util.Optional;

public interface HistoricoGateway {

    Optional<Historico> buscarPorId(Long id);

    Historico salvar(Historico historico);
}
