package br.com.mobsolutions.eventos.repositories;

import java.time.LocalDate;
import java.util.List;

import br.com.mobsolutions.eventos.domain.models.Evento;
import br.com.mobsolutions.eventos.domain.models.Evento_;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class EventoRepository extends JpaEntityManagerRepository<Evento, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public EventoRepository() {
        super(Evento.class);
    }

    public List<Evento> findByNomeLikeAndDataInicioAndDataFim(String nome, LocalDate dataInicio, LocalDate dataFim) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Evento> query = builder.createQuery(Evento.class);
        Root<Evento> evento = query.from(Evento.class);

        Predicate filtros = builder.and();

        if (nome != null && !nome.isBlank()) {
            filtros = builder.and(builder.like(evento.get(Evento_.nome), "%".concat(nome).concat("%")));
        }

        if (dataInicio != null) {
            filtros = builder.and(builder.equal(evento.get(Evento_.dataInicio), dataInicio));
        }

        if (dataFim != null) {
            filtros = builder.and(builder.equal(evento.get(Evento_.dataFim), dataFim));
        }

        return entityManager.createQuery(query.where(filtros)).getResultList();

    }

}
