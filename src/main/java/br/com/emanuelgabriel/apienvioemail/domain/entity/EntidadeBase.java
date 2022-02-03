package br.com.emanuelgabriel.apienvioemail.domain.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class EntidadeBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="VERSAO")
    private Long versao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATA_ULT_ALTERACAO")
    private Date dataUltimaAlteracao = new Date();

    @Column(name="USUARIO_ULT_ALTERACAO")
    private String usuarioUltimaAlteracao = "recuperacao.senha";


}