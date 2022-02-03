package br.com.emanuelgabriel.apienvioemail.controller;

import br.com.emanuelgabriel.apienvioemail.domain.mapper.request.EmailRequestDTO;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.request.UsuarioRequestDTO;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.response.EmailResponseDTO;
import br.com.emanuelgabriel.apienvioemail.domain.repository.UsuarioRepository;
import br.com.emanuelgabriel.apienvioemail.services.email.EmailService;
import br.com.emanuelgabriel.apienvioemail.services.exceptions.ObjetoNaoEncontradoException;
import br.com.emanuelgabriel.apienvioemail.services.exceptions.UsuarioNaoEncontradoException;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(value = "/enviar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> enviarEmail(@Valid @RequestBody UsuarioRequestDTO request) {
        log.info("GET /usuario/enviar {}", request);

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