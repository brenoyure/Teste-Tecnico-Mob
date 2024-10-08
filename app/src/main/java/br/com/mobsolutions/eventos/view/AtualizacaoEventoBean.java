package br.com.mobsolutions.eventos.view;

import java.io.Serializable;

import br.com.mobsolutions.eventos.domain.dto.evento.AtualizacaoEventoDto;
import br.com.mobsolutions.eventos.domain.services.EventoService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @ViewScoped
public class AtualizacaoEventoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

    private AtualizacaoEventoDto evento;

    @Inject
    private EventoService eventoService;

    public void carregarEventoPeloId(Long id) {
        eventoService
            .buscarPorId(id)
            .ifPresentOrElse(evento -> setEvento(new AtualizacaoEventoDto(evento)), 
                    () -> facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/eventos"));
    }

    public void atualizar() {
        eventoService.atualizar(evento);
        facesContext.addMessage(null, 
                new FacesMessage("Evento atualizado com sucesso"));
    }

    public AtualizacaoEventoDto getEvento() {
        return evento;
    }

    public void setEvento(AtualizacaoEventoDto evento) {
        this.evento = evento;
    }
    
}
