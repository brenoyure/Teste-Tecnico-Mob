package br.com.mobsolutions.eventos.repositories;

import static br.com.mobsolutions.eventos.domain.models.Participante_.evento;

import java.util.List;

import br.com.mobsolutions.eventos.domain.models.Evento_;
import br.com.mobsolutions.eventos.domain.models.Participante;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class ParticipanteRepository extends JpaEntityManagerRepository<Participante, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public ParticipanteRepository() {
        super(Participante.class);
    }

    public boolean existsByCpfAndEventoId(String cpf, Long eventoId) {
        try {
            return entityManager
                    .createQuery("SELECT EXISTS(SELECT p FROM Participante p WHERE p.cpf = ?1 AND p.evento.id = ?2)", Boolean.class)
                    .setParameter(1, cpf)
                    .setParameter(2, eventoId)
                    .getSingleResult();
        } catch(NoResultException e) { return false; }
    }

    public List<Participante> findAllByEventoId(Long eventoId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Participante> query = builder.createQuery(Participante.class);
        Root<Participante> participante = query.from(Participante.class);

        ParameterExpression<Long> pEventoId = builder.parameter(Long.class);

        return entityManager
                .createQuery(query.where(builder.equal(participante.get(evento).get(Evento_.id), pEventoId)))
                .setParameter(pEventoId, eventoId).getResultList();

    }

    public boolean deleteAllByEventoId(Long eventoId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Participante> deleteQuery = builder.createCriteriaDelete(Participante.class);
        Root<Participante> participante = deleteQuery.from(Participante.class);

        ParameterExpression<Long> pEventoId = builder.parameter(Long.class);

        return entityManager
                .createQuery(deleteQuery.where(builder.equal(participante.get(evento).get(Evento_.id), pEventoId)))
                .setParameter(pEventoId, eventoId)
                .executeUpdate() > 0;

    }

}
