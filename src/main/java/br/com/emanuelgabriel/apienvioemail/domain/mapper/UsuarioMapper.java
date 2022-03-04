package br.com.emanuelgabriel.apienvioemail.domain.mapper;

import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.response.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper implements EntityMapper<UsuarioResponseDTO, Usuario> {

    @Autowired
    private ModelMapper mapper;


    @Override
    public Usuario toEntity(UsuarioResponseDTO usuarioResponseDTO) {
        return mapper.map(usuarioResponseDTO, Usuario.class);
    }

    @Override
    public UsuarioResponseDTO toDto(Usuario usuario) {
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    @Override
    public List<Usuario> toEntity(List<UsuarioResponseDTO> usuarioResponseDTOS) {
        return usuarioResponseDTOS.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDTO> toDto(List<Usuario> usuarios) {
        return usuarios.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<UsuarioResponseDTO> mapEntityPageToDTO(Pageable pageable, Page<Usuario> pageEntity) {
        var listaDto = toDto(pageEntity.getContent());
        return new PageImpl<>(listaDto, pageable, pageEntity.getTotalElements());
    }
}
