package br.com.mobsolutions.eventos.domain.services;

import java.util.List;

import br.com.mobsolutions.eventos.domain.dto.participantes.AtualizaParticipanteDto;
import br.com.mobsolutions.eventos.domain.dto.participantes.NovoParticipanteDto;
import br.com.mobsolutions.eventos.domain.dto.participantes.ParticipanteDto;
import br.com.mobsolutions.eventos.domain.models.Evento;
import br.com.mobsolutions.eventos.domain.models.Participante;
import br.com.mobsolutions.eventos.repositories.EventoRepository;
import br.com.mobsolutions.eventos.repositories.ParticipanteRepository;
import br.com.mobsolutions.eventos.repositories.PresencaRepository;

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
    private PresencaRepository presencaRepository;

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

    @Transactional
    public void atualizar(@Valid AtualizaParticipanteDto dadosAtualizadosDoParticipante) {
        if (participanteRepository.existsNotByIdAndCpfAndEventoId(dadosAtualizadosDoParticipante.getParticipanteId(), dadosAtualizadosDoParticipante.getCpf(), dadosAtualizadosDoParticipante.getEventoId())) {
            throw new ValidationException("Já existe outro Participante cadastrado no Evento com o CPF informado");
        }

        Evento evento = 
                eventoRepository.getReferenceById(dadosAtualizadosDoParticipante.getEventoId());

        participanteRepository
            .update(
                    new Participante(
                            dadosAtualizadosDoParticipante.getParticipanteId(),
                            dadosAtualizadosDoParticipante.getNome(), 
                            dadosAtualizadosDoParticipante.getEmail(), 
                            dadosAtualizadosDoParticipante.getCpf(), 
                            evento));
    }    

    public List<ParticipanteDto> listarParticipantesDoEventoPeloId(Long eventoId) {
        return participanteRepository.findAllByEventoId(eventoId);
    }

    @Transactional
    public boolean excluirPorId(Long participanteId) {
        presencaRepository.deleteByParticipanteId(participanteId);
        return participanteRepository.deleteById(participanteId);        
    }

}
