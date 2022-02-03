package br.com.emanuelgabriel.apienvioemail.domain.repository;

import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCpf(String cpf);

}
