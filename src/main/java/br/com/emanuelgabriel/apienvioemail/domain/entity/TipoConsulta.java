package br.com.emanuelgabriel.apienvioemail.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_TIPO_CONSULTA")
public class TipoConsulta extends EntidadeBase {

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

}
