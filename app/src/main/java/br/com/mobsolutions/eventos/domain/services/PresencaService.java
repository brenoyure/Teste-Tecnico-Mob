package br.com.mobsolutions.eventos.domain.services;

import java.time.LocalDate;

import br.com.mobsolutions.eventos.domain.dto.participantes.NovaPresencaDto;
import br.com.mobsolutions.eventos.domain.models.Evento;
import br.com.mobsolutions.eventos.domain.models.Participante;
import br.com.mobsolutions.eventos.domain.models.Presenca;
import br.com.mobsolutions.eventos.repositories.EventoRepository;
import br.com.mobsolutions.eventos.repositories.ParticipanteRepository;
import br.com.mobsolutions.eventos.repositories.PresencaRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RequestScoped
public class PresencaService {

    @Inject
    private PresencaRepository presencaRepository;

    @Inject
    private EventoRepository eventoRepository;

    @Inject
    private ParticipanteRepository participanteRepository;

    @Transactional
    public Presenca marcarPresenca(@Valid NovaPresencaDto novaPresenca) {

        /*
         * Carregamos as entidades evento e participante, utilizando o findById(entityManager.find()) 
         * ao invés do getReference(entityManager.getReference()), pois das informações do evento,
         * como as datas de inicio e fim, e das informações do participante.
         */

        Evento evento = 
                eventoRepository.findById(novaPresenca.getEventoId()).orElseThrow();

        Participante participante = 
                participanteRepository.findById(novaPresenca.getParticipanteId()).orElseThrow();

        /*
         * Valida se está sendo marcada presença para data antes do evento
         */
        if (novaPresenca.getData().isBefore(evento.getDataInicio())) {
            throw new ValidationException("Não é permitido marcar presença para data antes do evento");
        }

        /*
         * Valida se está sendo marcada presença para data após o evento
         */
        if (novaPresenca.getData().isAfter(evento.getDataFim())) {
            throw new ValidationException("Não é permitido marcar presença para data após o evento");
        }        

        /*
         * Valida se está sendo marcada mais de uma presença na mesma data
         */
        if (presencaRepository.existsByDataAndParticipanteCpf(novaPresenca.getData(), participante.getCpf())) {
            LocalDate data = novaPresenca.getData();
            final String exceptionMessage = 
                    String.format("Participante %s já marcou presença em outro evento na data %d/%d/%d", participante.getNome(), data.getDayOfMonth(), data.getMonthValue(), data.getYear());
            throw new ValidationException(exceptionMessage);
        }

        return presencaRepository.save(new Presenca(novaPresenca.getData(), participante));

    }

}
