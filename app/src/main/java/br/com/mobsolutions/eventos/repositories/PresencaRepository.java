package br.com.mobsolutions.eventos.repositories;

import java.time.LocalDate;

import br.com.mobsolutions.eventos.domain.models.Participante_;
import br.com.mobsolutions.eventos.domain.models.Presenca;
import br.com.mobsolutions.eventos.domain.models.Presenca_;

import jakarta.enterprise.context.RequestScoped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

@RequestScoped
public class PresencaRepository extends JpaEntityManagerRepository<Presenca, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public PresencaRepository() {
        super(Presenca.class);
    }

    public boolean deleteByParticipanteId(Long participanteId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Presenca> deleteQuery = builder.createCriteriaDelete(Presenca.class);
        Root<Presenca> presenca = deleteQuery.from(Presenca.class);

        ParameterExpression<Long> pParticipanteId = builder.parameter(Long.class);

        return entityManager
                .createQuery(deleteQuery.where(builder.equal(presenca.get(Presenca_.participante).get(Participante_.id), pParticipanteId)))
                .setParameter(pParticipanteId, participanteId)
                .executeUpdate() > 0;
    }

    public boolean existsByDataAndParticipanteCpf(LocalDate data, String cpf) {
        try {
            return entityManager
                    .createQuery("SELECT EXISTS(SELECT p FROM Presenca p WHERE p.data = ?1 AND p.participante.cpf = ?2)", Boolean.class)
                    .setParameter(1, data)
                    .setParameter(2, cpf)
                    .getSingleResult();
        } catch (NoResultException e) { return false; }

    }

    public boolean deleteAllByParticipanteEventoId(Long eventoId) {
        return entityManager
                .createQuery("DELETE FROM Presenca p WHERE p.participante.id IN ( SELECT(participante.id) FROM Participante participante WHERE participante.evento.id = ?1 )")
                .setParameter(1, eventoId)
                .executeUpdate() > 0;
    }

}
