package br.com.emanuelgabriel.apienvioemail.domain.mapper.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotNull(message = "O cpf não pode ser nulo")
    private String cpf;

    @NotNull(message = "O email não pode ser nulo")
    private String email;

}
