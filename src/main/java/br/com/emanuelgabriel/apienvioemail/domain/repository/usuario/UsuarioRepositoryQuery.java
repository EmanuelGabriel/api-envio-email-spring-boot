package br.com.emanuelgabriel.apienvioemail.domain.repository.usuario;

import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.response.UsuarioGridResponseDTO;
import br.com.emanuelgabriel.apienvioemail.domain.repository.filter.UsuarioFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepositoryQuery {

    Page<UsuarioGridResponseDTO> resumo(UsuarioFiltro filtro, Pageable pageable);
}
