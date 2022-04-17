package br.com.emanuelgabriel.apienvioemail.domain.mapper.response;

<<<<<<< HEAD
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

>>>>>>> 7cfa60a057a9e3f76fd7feed9f6aa0d332216a31
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

<<<<<<< HEAD
	private static final long serialVersionUID = 1L;
	private Long id;
	private String usuario;
	private String nome;
	private String cpf;
	private String email;
	private String cpfConsultado;
=======
    private Long id;
    private String usuario;
    private String nome;
    private String cpf;
    private String email;
    private String cpfConsultado;
    private LocalDate dataSolicitacao;
>>>>>>> 7cfa60a057a9e3f76fd7feed9f6aa0d332216a31

	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataSolicitacao;

}
