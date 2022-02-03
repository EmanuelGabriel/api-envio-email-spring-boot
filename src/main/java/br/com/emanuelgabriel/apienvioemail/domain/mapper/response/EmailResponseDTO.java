package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponseDTO {

    private String emailOrigem;
    private String assunto;
    private String conteudo;
}
