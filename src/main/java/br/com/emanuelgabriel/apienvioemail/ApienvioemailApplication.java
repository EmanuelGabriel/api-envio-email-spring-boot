package br.com.emanuelgabriel.apienvioemail;

import br.com.emanuelgabriel.apienvioemail.domain.entity.HistoricoSolicitacao;
import br.com.emanuelgabriel.apienvioemail.domain.entity.TipoConsulta;
import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import br.com.emanuelgabriel.apienvioemail.domain.repository.HistoricoSolicitacaoRepository;
import br.com.emanuelgabriel.apienvioemail.domain.repository.TipoConsultaRepository;
import br.com.emanuelgabriel.apienvioemail.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootApplication
public class ApienvioemailApplication implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HistoricoSolicitacaoRepository historicoSolicitacaoRepository;

    @Autowired
    private TipoConsultaRepository tipoConsultaRepository;

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

        // CRIAR TIPO DE CONSULTA
        var tipoConsulta = tipoConsultaRepository.buscarPorID(1L);
        if (tipoConsulta == null) {
            tipoConsulta = new TipoConsulta();
            tipoConsulta.setDescricao("Analítica");
            tipoConsultaRepository.save(tipoConsulta);
        }

        if (historico == null) {
            historico = new HistoricoSolicitacao();
            historico.setUsuario(usuarioJoao);
            historico.setCpfConsultado(usuarioJoao.getCpf());
            historico.setDataSolicitacao(LocalDate.of(2021, 12, 28));
            historico.setTipoConsulta(tipoConsulta);
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

        // CRIAR TIPO DE CONSULTA
        var tipoConsulta02 = tipoConsultaRepository.buscarPorID(2L);
        if (tipoConsulta02 == null) {
            tipoConsulta02 = new TipoConsulta();
            tipoConsulta02.setDescricao("COMPLETA");
            tipoConsultaRepository.save(tipoConsulta02);
        }

        if (historicoMaria == null) {
            historicoMaria = new HistoricoSolicitacao();
            historicoMaria.setUsuario(usuarioMaria);
            historicoMaria.setCpfConsultado(usuarioMaria.getCpf());
            historicoMaria.setDataSolicitacao(LocalDate.of(2022, 02, 01));
            historicoMaria.setTipoConsulta(tipoConsulta02);
            historicoSolicitacaoRepository.save(historicoMaria);
        }
    }
}
