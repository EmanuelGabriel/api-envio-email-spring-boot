package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGridResponseDTO implements Serializable {

    private Long id;
    private String usuario;
    private String nome;
    private String cpf;
    private String email;
    private String cpfConsultado;
    private LocalDate dataSolicitacao;


}
