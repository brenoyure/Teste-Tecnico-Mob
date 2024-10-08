package br.com.mobsolutions.eventos.domain.services;

import java.util.Optional;

import br.com.mobsolutions.eventos.domain.dto.evento.AtualizacaoEventoDto;
import br.com.mobsolutions.eventos.domain.dto.evento.NovoEventoDto;
import br.com.mobsolutions.eventos.domain.models.Evento;
import br.com.mobsolutions.eventos.repositories.EventoRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RequestScoped
public class EventoService {

    @Inject
    private EventoRepository eventoRepository;

    @Transactional
    public Evento cadastrar(@Valid NovoEventoDto dados) {
        Evento evento = new Evento(dados.getNome(), dados.getDataInicial(), dados.getDataFinal());
        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento atualizar(@Valid AtualizacaoEventoDto dadosAtualizados) {
        Evento evento = new Evento(dadosAtualizados.getNome(), dadosAtualizados.getDataInicial(), dadosAtualizados.getDataFinal());
        evento.setId(dadosAtualizados.getId());
        return eventoRepository.update(evento);
    }

    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

}