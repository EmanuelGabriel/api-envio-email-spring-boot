package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataSolicitacao;


}
