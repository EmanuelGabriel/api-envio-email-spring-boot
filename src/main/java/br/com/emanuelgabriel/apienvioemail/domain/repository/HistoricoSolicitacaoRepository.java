package br.com.emanuelgabriel.apienvioemail.domain.repository;

import br.com.emanuelgabriel.apienvioemail.domain.entity.HistoricoSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoSolicitacaoRepository extends JpaRepository<HistoricoSolicitacao, Long> {

    @Query(value = "SELECT hs FROM HistoricoSolicitacao hs WHERE hs.id = :id")
    HistoricoSolicitacao buscarPorID(@Param(value = "id") Long id);

}
