package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO implements Serializable {

    private Long id;
    private String usuario;
    private String nome;
    private String cpf;
    private String email;
    private List<HistoricoResponseDTO> historico;
}
