package br.com.flowbank.account_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "*") 
public class ContaController {

    @Autowired
    private ContaRepository repository;

    
    @GetMapping("/{username}")
    public Conta getConta(@PathVariable String username) {
        
        return repository.findByUsuarioUsername(username)
                .orElse(new Conta(username, 0.0));
    }

    
    @PostMapping("/lancamento")
    public Conta adicionarLancamento(@RequestParam String username, 
                                     @RequestParam String descricao, 
                                     @RequestParam Double valor) {
        
        Optional<Conta> contaOpt = repository.findByUsuarioUsername(username);
        Conta conta;

        if (contaOpt.isPresent()) {
            conta = contaOpt.get();
        } else {
            conta = new Conta(username, 1500.00); 
        }

        conta.adicionarLancamento(descricao, valor);
        return repository.save(conta);
    }
}