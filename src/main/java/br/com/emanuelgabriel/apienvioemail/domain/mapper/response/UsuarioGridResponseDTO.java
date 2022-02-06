package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGridResponseDTO {

    private Long id;
    private String senha;
    private String usuario;
    private String nome;
    private String cpf;
    private String email;
    private String token;

}
