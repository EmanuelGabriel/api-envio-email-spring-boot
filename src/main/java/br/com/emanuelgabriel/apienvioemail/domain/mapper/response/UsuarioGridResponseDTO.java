package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGridResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
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
