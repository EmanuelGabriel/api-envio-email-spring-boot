package br.com.emanuelgabriel.apienvioemail;

import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import br.com.emanuelgabriel.apienvioemail.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApienvioemailApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApienvioemailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var usuario = usuarioRepository.findByCpf("02968496344");
        var cpfJoao = usuario != null ? usuario.getCpf() : "02968496344";

        if (usuario == null) {
            usuario = new Usuario();
            usuario.setNome("João");
            usuario.setEmail("fulano123@hotmail.com");
            usuario.setSenha("123456");
            usuario.setCpf(cpfJoao);
            usuario.setUsuario("João");
            usuarioRepository.save(usuario);
        }

        usuario = usuarioRepository.findByCpf("32559118009");
        String cpfMaria = usuario != null ? usuario.getCpf() : "32559118009";

        if (usuario == null) {
            usuario = new Usuario();
            usuario.setNome("Maria");
            usuario.setEmail("maria123@gmail.com");
            usuario.setSenha("123456789");
            usuario.setCpf(cpfMaria);
            usuario.setUsuario("Maria");
            usuarioRepository.save(usuario);
        }
    }
}
