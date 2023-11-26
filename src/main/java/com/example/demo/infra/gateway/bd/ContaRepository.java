package com.example.demo.infra.gateway.bd;

import com.example.demo.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta findByCpf(String cpf);
}
