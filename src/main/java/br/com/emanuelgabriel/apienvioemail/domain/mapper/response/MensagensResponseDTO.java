package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author emanuel.sousa
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensagensResponseDTO {

    private String mensagem;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime dataHora = LocalDateTime.now();

    public MensagensResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @param erros
     * @return
     */
    public static List<MensagensResponseDTO> toList(BindingResult erros) {
        if (erros.hasErrors()) {
            List<MensagensResponseDTO> mensagens = new ArrayList<>();
            erros.getAllErrors().forEach((e) -> {
                mensagens.add(new MensagensResponseDTO(e.getDefaultMessage()));
            });
            return mensagens;
        }
        return new ArrayList<>();
    }

}
