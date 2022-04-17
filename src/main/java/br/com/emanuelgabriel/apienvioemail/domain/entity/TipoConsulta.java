package br.com.emanuelgabriel.apienvioemail.domain.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_TIPO_CONSULTA")
public class TipoConsulta extends EntidadeBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "DESCRICAO", nullable = false)
    private String descricao;

}
