package br.com.emanuelgabriel.apienvioemail.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_HISTORICO_SOLICITACAO")
public class HistoricoSolicitacao extends EntidadeBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HISTORICO")
    private Long id;

    @Column(name = "DATA_SOLICITACAO")
    private LocalDate dataSolicitacao;

    @Column(name = "CPF_CONSULTADO", nullable = false)
    private String cpfConsultado;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_CONSULTA", nullable = false)
    private TipoConsulta tipoConsulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;

}
