package br.com.mobsolutions.eventos.view;

import java.io.Serializable;
import java.util.List;

import br.com.mobsolutions.eventos.domain.dto.participantes.NovoParticipanteDto;
import br.com.mobsolutions.eventos.domain.models.Participante;
import br.com.mobsolutions.eventos.domain.services.ParticipanteService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ValidationException;

@Named @ViewScoped
public class ParticipantesEventoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

    @Inject
    private ParticipanteService participanteService;

    @Inject
    private NovoParticipanteDto novoParticipante;    

    private List<Participante> participantes;

    public String cadastrarNovoParticipante() {
        try {
            participanteService.cadastrar(novoParticipante);
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, 
                    new FacesMessage("Cadastro Realizado com Sucesso", novoParticipante.getNome() + " agora é um Participante do evento"));
            return facesContext.getViewRoot().getViewId().concat("?eventoId=" + novoParticipante.getEventoId() + "&faces-redirect=true");
        } catch (ValidationException e) { 
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), null));
            return null;
        }
    }

    public String excluir(long participanteId) {
        participanteService.excluirPorId(participanteId);
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null, new FacesMessage("Participante excluído com sucesso"));
        return facesContext.getViewRoot().getViewId().concat("?eventoId=").concat(novoParticipante.getEventoId().toString()).concat("&faces-redirect=true");
    }

    public void carregaParticipantesPeloIdDoEvento(Long id) {
        novoParticipante.setEventoId(id);
        setParticipantes(participanteService.listarParticipantesDoEventoPeloId(id));
    }

    public NovoParticipanteDto getNovoParticipante() {
        return novoParticipante;
    }

    public void setDadosParticipante(NovoParticipanteDto novoParticipante) {
        this.novoParticipante = novoParticipante;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

}
