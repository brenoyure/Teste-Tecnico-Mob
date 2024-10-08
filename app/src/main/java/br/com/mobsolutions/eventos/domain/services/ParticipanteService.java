package br.com.mobsolutions.eventos.domain.services;

import java.util.List;

import br.com.mobsolutions.eventos.domain.dto.participantes.NovoParticipanteDto;
import br.com.mobsolutions.eventos.domain.models.Evento;
import br.com.mobsolutions.eventos.domain.models.Participante;
import br.com.mobsolutions.eventos.repositories.EventoRepository;
import br.com.mobsolutions.eventos.repositories.ParticipanteRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RequestScoped
public class ParticipanteService {

    @Inject
    private ParticipanteRepository participanteRepository;

    @Inject
    private EventoRepository eventoRepository;

    @Transactional
    public Participante cadastrar(@Valid NovoParticipanteDto novoParticipante) {
        if (participanteRepository.existsByCpfAndEventoId(novoParticipante.getCpf(), novoParticipante.getEventoId())) {
            throw new ValidationException("O Participante com o CPF informado já está cadastrado no Evento");
        }

        Evento evento = 
                eventoRepository.getReferenceById(novoParticipante.getEventoId());

        return participanteRepository.save(new Participante(novoParticipante.getNome(), novoParticipante.getEmail(), novoParticipante.getCpf(), evento));
    }

    public List<Participante> listarParticipantesDoEventoPeloId(Long eventoId) {
        return participanteRepository.findAllByEventoId(eventoId);
    }

    @Transactional
    public boolean excluirPorId(Long participanteId) {
        return participanteRepository.deleteById(participanteId);        
    }

}
