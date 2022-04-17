package br.com.emanuelgabriel.apienvioemail.domain.repository.usuario;

import br.com.emanuelgabriel.apienvioemail.domain.entity.Usuario;
import br.com.emanuelgabriel.apienvioemail.domain.mapper.response.UsuarioGridResponseDTO;
import br.com.emanuelgabriel.apienvioemail.domain.repository.filter.UsuarioFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

    // (type = PersistenceContextType.EXTENDED)
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<UsuarioGridResponseDTO> filtrarPor(UsuarioFilter filtro, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<UsuarioGridResponseDTO> criteriaQuery = builder.createQuery(UsuarioGridResponseDTO.class);

        Root<Usuario> root = criteriaQuery.from(Usuario.class);

        From<?, ?> historicoJoin = root.join("historico", JoinType.INNER);

        criteriaQuery.select(builder.construct(UsuarioGridResponseDTO.class,
                root.get("id"),
                root.get("usuario"),
                root.get("nome"),
                root.get("cpf"),
                root.get("email"),
//                historicoJoin.get("id"),
                historicoJoin.get("cpfConsultado"),
                historicoJoin.get("dataSolicitacao")

        ));

        Predicate[] predicates = criarRestricoesFiltro(filtro, builder, root, historicoJoin);

        criteriaQuery.where(predicates);
        TypedQuery<UsuarioGridResponseDTO> query = manager.createQuery(criteriaQuery);
        var lista = query.getResultList();
        adicionarRestrincoesDePaginacao(query, pageable);
        return new PageImpl<>(lista, pageable, total(filtro));
    }

    /**
     * @param filtro filtro a ser executado
     * @param builder builder
     * @param root root
     * @return predicate[]
     */
    private Predicate[] criarRestricoesFiltro(UsuarioFilter filtro, CriteriaBuilder builder, Root<Usuario> root, From<?, ?> historicoJoin) {

        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(filtro.getUsuario())) {
            predicates.add(builder.like(builder.lower(root.get("usuario")), "%" + filtro.getUsuario().toLowerCase() + "%"));
        }

        if (!ObjectUtils.isEmpty(filtro.getNome())) {
            predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
        }

        if (!ObjectUtils.isEmpty(filtro.getCpf())) {
            predicates.add(builder.like(builder.lower(root.get("cpf")), "%" + filtro.getCpf() + "%"));
        }

        if (!ObjectUtils.isEmpty(filtro.getEmail())) {
            predicates.add(builder.like(builder.lower(root.get("email")), "%" + filtro.getEmail().toLowerCase() + "%"));
        }

        if (!ObjectUtils.isEmpty(filtro.getCpfConsultado())) {
            predicates.add(builder.equal(builder.lower(historicoJoin.get("cpfConsultado")), filtro.getCpfConsultado()));
        }

        if (!ObjectUtils.isEmpty(filtro.getDataSolicitacao())) {
            predicates.add(builder.equal(builder.lower(historicoJoin.get("dataSolicitacao")), filtro.getDataSolicitacao()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    /**
     * @param query query a ser executada
     * @param pageable modo de paginação
     */
    private void adicionarRestrincoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    /**
     * @param filter
     * @return long
     */
    private Long total(UsuarioFilter filter) {
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
