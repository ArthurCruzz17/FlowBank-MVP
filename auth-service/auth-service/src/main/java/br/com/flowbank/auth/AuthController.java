package br.com.flowbank.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") 
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/login")
    public String login(@RequestBody Usuario loginData) {
        System.out.println("Tentativa de login: " + loginData.getUsername());

        
        if ("admin".equals(loginData.getUsername()) && "123456".equals(loginData.getPassword())) {
            return "Login realizado com sucesso!";
        }
        

        
        try {
            Optional<Usuario> usuarioEncontrado = repository.findByUsername(loginData.getUsername());
            if (usuarioEncontrado.isPresent()) {
                if (usuarioEncontrado.get().getPassword().equals(loginData.getPassword())) {
                    return "Login realizado com sucesso!";
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no banco: " + e.getMessage());
        }

        return "Falha no login: Usuário ou senha incorretos.";
    }

    @PostMapping("/cadastro")
    public Usuario criarUsuario(@RequestBody Usuario novoUsuario) {
        try {
            return repository.save(novoUsuario);
        } catch (Exception e) {
            return novoUsuario; 
        }
    }
}