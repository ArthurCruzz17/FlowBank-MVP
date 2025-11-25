package br.com.flowbank.account_service;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    
    Optional<Conta> findByUsuarioUsername(String usuarioUsername);
}