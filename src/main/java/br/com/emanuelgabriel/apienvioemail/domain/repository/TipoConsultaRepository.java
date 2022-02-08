package br.com.emanuelgabriel.apienvioemail.domain.repository;

import br.com.emanuelgabriel.apienvioemail.domain.entity.TipoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoConsultaRepository extends JpaRepository<TipoConsulta, Long> {

    @Query(value = "SELECT tc FROM TipoConsulta tc WHERE tc.id = :id")
    TipoConsulta buscarPorID(@Param(value = "id") Long id);
}
