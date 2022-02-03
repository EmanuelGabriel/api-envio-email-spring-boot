package br.com.emanuelgabriel.apienvioemail.domain.mapper.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDTO {

    private String emailOrigem;
    private String emailDestino;
    private String assunto;
    private String conteudo;
    private Map<String, String> model;


}
