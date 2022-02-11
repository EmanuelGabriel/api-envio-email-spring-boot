package br.com.emanuelgabriel.apienvioemail.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria extends EntidadeBase {

    @Column(name = "nome", nullable = false, length = 40)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

}
