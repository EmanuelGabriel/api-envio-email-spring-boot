package br.com.emanuelgabriel.apienvioemail.domain.repository.usuario;

import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.response.UsuarioGridResponseDTO;
import br.com.emanuelgabriel.apienvioemail.domain.repository.filter.UsuarioFiltro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager manager;

    @Override
    public Page<UsuarioGridResponseDTO> resumo(UsuarioFiltro filtro, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<UsuarioGridResponseDTO> criteriaQuery = builder.createQuery(UsuarioGridResponseDTO.class);

        Root<Usuario> root = criteriaQuery.from(Usuario.class);

        From<?, ?> historicoJoin = root.join("historico", JoinType.INNER);

        criteriaQuery.select(builder.construct(UsuarioGridResponseDTO.class,
                root.get("id"),
                root.get("usuario"),
                root.get("nome"),
                //historicoJoin.get("id"),
                historicoJoin.get("token")
        ));

        Predicate[] predicates = criarRestricoesFiltro(filtro, builder, root, historicoJoin);

        criteriaQuery.where(predicates);
        TypedQuery<UsuarioGridResponseDTO> query = manager.createQuery(criteriaQuery);
        var lista = query.getResultList();
        adicionarRestrincoesDePaginacao(query, pageable);
        return new PageImpl<>(lista, pageable, total(filtro));
    }

    /**
     * @param filtro
     * @return List<Usuario>
     */
    @Override
    public List<UsuarioGridResponseDTO> filtrarPor(UsuarioFiltro filtro) {
        log.info("FiltrarPor {}", filtro);

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<UsuarioGridResponseDTO> criteria = builder.createQuery(UsuarioGridResponseDTO.class);

        // ADICIONAR AS RESTRIÇÕES - FILTROS
        Root<Usuario> root = criteria.from(Usuario.class);

        // INNER JOIN ou o JOIN na TABELA 'HISTORICO'
        From<?, ?> historicoJoin = root.join("historico", JoinType.INNER);

        // CRIAR AS RESTRIÇÕES
        Predicate[] predicates = criarRestricoesFiltro(filtro, builder, root, historicoJoin);
        criteria.where(predicates);

        TypedQuery<UsuarioGridResponseDTO> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * @param filtro
     * @param builder
     * @param root
     * @return predicate[]
     * From<?, ?> historicoJoin
     */
    private Predicate[] criarRestricoesFiltro(UsuarioFiltro filtro, CriteriaBuilder builder, Root<Usuario> root, From<?, ?> historicoJoin) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(filtro.getUsuario())) {
            predicates.add(builder.like(builder.lower(root.get("usuario")), "%" + filtro.getUsuario().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(filtro.getNome())) {
            predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
        }


        if (!StringUtils.isEmpty(filtro.getToken())) {
            predicates.add(builder.like(builder.lower(historicoJoin.get("token")), "%" + filtro.getToken().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    /**
     * @param query
     * @param pageable
     */
    private void adicionarRestrincoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    /**
     *
     * @param filter
     * @return long
     */
    private Long total(UsuarioFiltro filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Usuario> root = criteria.from(Usuario.class);
        From<?, ?> historicoJoin = root.join("historico", JoinType.INNER);

        Predicate[] predicates = criarRestricoesFiltro(filter, builder, root, historicoJoin);
        criteria.where(predicates);
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
