package br.com.emanuelgabriel.apienvioemail.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_historico_solicitacao")
public class HistoricoSolicitacao extends EntidadeBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HISTORICO")
    private Long id;

    @Column(name = "DATA_SOLICITACAO")
    private Date dataSolicitacao;

    @Column(name = "DATA_EXPIRACAO_")
    private Date dataExpiracao;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "EXPIRADO")
    private boolean expirado;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;


}
