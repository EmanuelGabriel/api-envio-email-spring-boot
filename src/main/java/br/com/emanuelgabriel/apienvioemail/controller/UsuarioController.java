package br.com.emanuelgabriel.apienvioemail.controller;

import br.com.emanuelgabriel.apienvioemail.domain.mapper.request.EmailRequestDTO;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.request.UsuarioRequestDTO;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.response.UsuarioGridResponseDTO;
import br.com.emanuelgabriel.apienvioemail.domain.repository.UsuarioRepository;
import br.com.emanuelgabriel.apienvioemail.domain.repository.filter.UsuarioFilter;
import br.com.emanuelgabriel.apienvioemail.services.email.EmailService;
import br.com.emanuelgabriel.apienvioemail.services.exceptions.ObjetoNaoEncontradoException;
import br.com.emanuelgabriel.apienvioemail.services.exceptions.UsuarioNaoEncontradoException;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;


/**
 * @author emanuel.sousa
 */

@Slf4j
@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Page<UsuarioGridResponseDTO>> resumo(UsuarioFilter filtro, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("GET /usuarios - filtro {}; PageNumber:{}; PageSize:{}", filtro, pageable.getPageNumber(), pageable.getPageSize());
        var pageFiltro = usuarioRepository.resumo(filtro, pageable);
        return pageFiltro != null ? ResponseEntity.ok().body(pageFiltro) : ResponseEntity.ok().build();
    }


    @GetMapping(value = "/enviar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> enviarEmail(@Valid @RequestBody UsuarioRequestDTO request) {
        log.info("GET /usuarios/enviar {}", request);

        var usuario = usuarioRepository.findByCpf(request.getCpf());
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuário de CPF ".concat(usuario.getCpf()).concat(" não encontrado"));
        }

        try {

            var emailDto = new EmailRequestDTO();
            emailDto.setEmailOrigem("emanuel.gabriel.sousa@hotmail.com");
            emailDto.setEmailDestino(usuario.getEmail());
            emailDto.setAssunto("Seu link de acesso");
            emailService.enviarEmail(emailDto, usuario.getNome(), "/validar?token=" + usuario.getId());

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new ObjetoNaoEncontradoException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ObjetoNaoEncontradoException("Impossível enviar o e-mail");
        } catch (TemplateException e) {
            e.printStackTrace();
            throw new ObjetoNaoEncontradoException("Impossível enviar o e-mail");
        }

        return usuario != null ? ResponseEntity.ok().body(usuario) : ResponseEntity.badRequest().build();
    }

}
