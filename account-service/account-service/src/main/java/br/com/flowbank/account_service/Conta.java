package br.com.flowbank.account_service;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String usuarioUsername; 
    private Double saldo;

    
    @ElementCollection
    private List<String> extrato = new ArrayList<>();

    
    public Conta() {}

    public Conta(String usuarioUsername, Double saldo) {
        this.usuarioUsername = usuarioUsername;
        this.saldo = saldo;
    }

    
    public void adicionarLancamento(String lancamento, Double valor) {
        this.extrato.add(lancamento);
        this.saldo -= valor; 
    }

    
    public Long getId() { return id; }
    public String getUsuarioUsername() { return usuarioUsername; }
    public void setUsuarioUsername(String usuarioUsername) { this.usuarioUsername = usuarioUsername; }
    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
    public List<String> getExtrato() { return extrato; }
    public void setExtrato(List<String> extrato) { this.extrato = extrato; }
}