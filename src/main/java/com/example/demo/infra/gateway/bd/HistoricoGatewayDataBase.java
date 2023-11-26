package com.example.demo.infra.gateway.bd;

import com.example.demo.domain.gateway.HistoricoGateway;
import com.example.demo.domain.model.Historico;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HistoricoGatewayDataBase implements HistoricoGateway {
    HistoricoRepository historicoRepository;

    public HistoricoGatewayDataBase(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    @Override
    public Optional<Historico> buscarPorId(Long id) {
        return historicoRepository.findById(id);
    }

    @Override
    public Historico salvar(Historico historico) {
        return historicoRepository.save(historico);
    }

}
