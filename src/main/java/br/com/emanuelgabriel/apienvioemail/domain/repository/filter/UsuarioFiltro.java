package br.com.emanuelgabriel.apienvioemail.domain.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFiltro {

    private String usuario;
    private String nome;
    private String token;

}
