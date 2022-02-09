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
        var usuario01 = usuarioRepository.findByCpf("02968496344");
        var cpfJoao = usuario01 != null ? usuario01.getCpf() : "02968496344";

        // PRIMEIRO SALVO O OBJETO DO TIPO 'USUARIO'
        if (usuario01 == null) {
            usuario01 = new Usuario();
            usuario01.setNome("João Pedro");
            usuario01.setEmail("joaopedro123@hotmail.com");
            usuario01.setSenha("123456");
            usuario01.setCpf(cpfJoao);
            usuario01.setUsuario("joao");
            usuarioRepository.save(usuario01);
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
            historico.setUsuario(usuario01);
            historico.setCpfConsultado(usuario01.getCpf());
            historico.setDataSolicitacao(LocalDate.of(2021, 12, 28));
            historico.setTipoConsulta(tipoConsulta);
            historicoSolicitacaoRepository.save(historico);
        }

        // PRIMEIRO SALVO O OBJETO DO TIPO 'USUARIO'
        var usuario02 = usuarioRepository.findByCpf("32559118009");
        String cpfMaria = usuario02 != null ? usuario02.getCpf() : "32559118009";

        if (usuario02 == null) {
            usuario02 = new Usuario();
            usuario02.setNome("Maria Isabel");
            usuario02.setEmail("emanuel.gabriel.sousa@gmail.com");
            usuario02.setSenha("123456789");
            usuario02.setCpf(cpfMaria);
            usuario02.setUsuario("maria");
            usuarioRepository.save(usuario02);
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
            historicoMaria.setUsuario(usuario02);
            historicoMaria.setCpfConsultado(usuario02.getCpf());
            historicoMaria.setDataSolicitacao(LocalDate.of(2022, 02, 01));
            historicoMaria.setTipoConsulta(tipoConsulta02);
            historicoSolicitacaoRepository.save(historicoMaria);
        }
    }
}
