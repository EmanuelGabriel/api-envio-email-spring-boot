package br.com.emanuelgabriel.apienvioemail.domain.repository.usuario;

import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.response.UsuarioGridResponseDTO;
import br.com.emanuelgabriel.apienvioemail.domain.repository.filter.UsuarioFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<UsuarioGridResponseDTO> resumo(UsuarioFiltro filtro, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<UsuarioGridResponseDTO> criteria = builder.createQuery(UsuarioGridResponseDTO.class);
        Root<Usuario> root = criteria.from(Usuario.class);
        From<?, ?> historicoJoin = root.join("historico", JoinType.INNER);

        criteria.select(builder.construct(UsuarioGridResponseDTO.class,
                root.get("id"),
                root.get("senha"),
                root.get("usuario"),
                root.get("nome"),
                root.get("cpf"),
                historicoJoin.get("id"),
                historicoJoin.get("token")
        ));

        Sort sort = pageable.getSort();
//        if (sort != null) {
//            Sort.Order order = sort.iterator().next();
//            String property = order.getProperty();
//            criteria.orderBy(order.isAscending() ? builder.asc(root.get(property))
//                    : builder.desc(root.get(property)));
//        }


        TypedQuery<UsuarioGridResponseDTO> query = manager.createQuery(criteria);
        return new PageImpl<>(query.getResultList(), pageable, 10L);
    }
}
