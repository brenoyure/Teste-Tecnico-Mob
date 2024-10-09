package br.com.mobsolutions.eventos.view;

import java.io.Serializable;

import br.com.mobsolutions.eventos.domain.dto.participantes.NovaPresencaDto;
import br.com.mobsolutions.eventos.domain.services.PresencaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ValidationException;

@Named
@ViewScoped
public class CadastroPresencaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

    @Inject
    private PresencaService presencaService;

    @Inject
    private NovaPresencaDto novaPresenca;

    public String marcarPresenca(Long participanteId, Long eventoId) {
        try {
            novaPresenca.setEventoId(eventoId);
            novaPresenca.setParticipanteId(participanteId);

            presencaService.marcarPresenca(novaPresenca);

            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage("Presença marcada com sucesso"));

            return facesContext.getViewRoot().getViewId().concat("?eventoId=").concat(eventoId.toString()).concat("&faces-redirect=true");
        } catch (ValidationException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados Incorretos ao marcar presença", e.getMessage()));
            return null;
        }
    }

    public NovaPresencaDto getNovaPresenca() {
        return novaPresenca;
    }

    public void setNovaPresenca(NovaPresencaDto novaPresenca) {
        this.novaPresenca = novaPresenca;
    }
    
}
