package com.example.demo.infra.gateway.bd;

import com.example.demo.domain.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    Optional<Historico> findById(Long id);

    Historico save(Historico historico);
}
