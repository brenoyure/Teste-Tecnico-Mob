package br.com.mobsolutions.eventos.repositories;

import static br.com.mobsolutions.eventos.domain.models.Participante_.evento;

import java.util.List;

import br.com.mobsolutions.eventos.domain.dto.participantes.ParticipanteDto;
import br.com.mobsolutions.eventos.domain.models.Evento_;
import br.com.mobsolutions.eventos.domain.models.Participante;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
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

    public List<ParticipanteDto> findAllByEventoId(Long eventoId) {
        return entityManager.createQuery(
"""
SELECT 
    new br.com.mobsolutions.eventos.domain.dto.participantes.ParticipanteDto(p.id, p.nome, p.email, p.cpf, presenca.id, p.evento.id)
FROM Participante p LEFT JOIN Presenca presenca ON presenca.participante.id=p.id WHERE p.evento.id = ?1
""", ParticipanteDto.class)
                .setParameter(1, eventoId)
                .getResultList();
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
