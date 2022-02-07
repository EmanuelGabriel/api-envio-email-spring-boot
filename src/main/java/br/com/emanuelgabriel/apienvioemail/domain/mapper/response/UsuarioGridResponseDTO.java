package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGridResponseDTO implements Serializable {

    private Long id;
    private String usuario;
    private String nome;
    // private Long historicoId;
    private String token;


}
