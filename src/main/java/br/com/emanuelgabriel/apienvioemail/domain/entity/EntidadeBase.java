package br.com.emanuelgabriel.apienvioemail.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author emanuel.sousa
 * Entitade base
 */

@Data
@MappedSuperclass
public abstract class EntidadeBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;


}
