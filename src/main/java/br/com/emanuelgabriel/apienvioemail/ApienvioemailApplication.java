package br.com.emanuelgabriel.apienvioemail;

import br.com.emanuelgabriel.apienvioemail.domain.entity.HistoricoSolicitacao;
import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import br.com.emanuelgabriel.apienvioemail.domain.repository.HistoricoSolicitacaoRepository;
import br.com.emanuelgabriel.apienvioemail.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Transient;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class ApienvioemailApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HistoricoSolicitacaoRepository historicoSolicitacaoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApienvioemailApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {


        // CRIAR USUÁRIO
        var usuarioJoao = usuarioRepository.findByCpf("02968496344");
        var cpfJoao = usuarioJoao != null ? usuarioJoao.getCpf() : "02968496344";

        // PRIMEIRO SALVO O OBJETO DO TIPO 'USUARIO'
        if (usuarioJoao == null) {
            usuarioJoao = new Usuario();
            usuarioJoao.setNome("João Pedro");
            usuarioJoao.setEmail("joaopedro123@hotmail.com");
            usuarioJoao.setSenha("123456");
            usuarioJoao.setCpf(cpfJoao);
            usuarioJoao.setUsuario("joao");
            usuarioRepository.save(usuarioJoao);
        }

        // CRIAR HISTÓRICO DE SOLICITAÇÃO
        var historico = historicoSolicitacaoRepository.buscarPorID(1L);

        if (historico == null) {
            historico = new HistoricoSolicitacao();
            historico.setUsuario(usuarioJoao);
            historico.setDataExpiracao(Date.from(Instant.now()));
            historico.setExpirado(false);
            historico.setDataSolicitacao(Date.from(Instant.now()));
            historico.setToken("alsdkf9238çadsf9283çasdf2834çaksdf908234nsdfaksjdf82734lkad98234ka98d8f");
            historicoSolicitacaoRepository.save(historico);
        }

        // PRIMEIRO SALVO O OBJETO DO TIPO 'USUARIO'
        var usuarioMaria = usuarioRepository.findByCpf("32559118009");
        String cpfMaria = usuarioMaria != null ? usuarioMaria.getCpf() : "32559118009";

        if (usuarioMaria == null) {
            usuarioMaria = new Usuario();
            usuarioMaria.setNome("Maria Isabel");
            usuarioMaria.setEmail("maria123@gmail.com");
            usuarioMaria.setSenha("123456789");
            usuarioMaria.setCpf(cpfMaria);
            usuarioMaria.setUsuario("maria");
            usuarioRepository.save(usuarioMaria);
        }

        // CRIAR HISTÓRICO DE SOLICITAÇÃO
        var historicoMaria = historicoSolicitacaoRepository.buscarPorID(2L);

        if (historicoMaria == null) {
            historicoMaria = new HistoricoSolicitacao();
            historicoMaria.setUsuario(usuarioMaria);
            historicoMaria.setDataExpiracao(Date.from(Instant.now()));
            historicoMaria.setExpirado(true);
            historicoMaria.setDataSolicitacao(Date.from(Instant.now()));
            historicoMaria.setToken("AA89LouweoioAH9083LAalsdkALlasdkALDOWU928L8KAqÇSO8ERJAoei9als82kKAJS8");
            historicoSolicitacaoRepository.save(historicoMaria);
        }
    }
}
