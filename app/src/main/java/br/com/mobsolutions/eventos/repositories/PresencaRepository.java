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


    public boolean existsByDataAndParticipanteId(LocalDate data, Long participanteId) {
        try {
            return entityManager
                    .createQuery("SELECT EXISTS(SELECT p FROM Presenca p WHERE p.data = ?1 AND p.participante.id = ?2)", Boolean.class)
                    .setParameter(1, data)
                    .setParameter(2, participanteId)
                    .getSingleResult();
        } catch (NoResultException e) { return false; }

    }

}
