package br.com.mobsolutions.eventos.view;

import java.io.Serializable;

import br.com.mobsolutions.eventos.domain.dto.participantes.NovaPresencaDto;
import br.com.mobsolutions.eventos.domain.dto.participantes.ParticipanteDto;
import br.com.mobsolutions.eventos.domain.services.PresencaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

@Named @ViewScoped
public class CadastroPresencaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

    @Inject
    private PresencaService presencaService;

    public String marcarPresenca(ParticipanteDto participante) {
        try {
            NovaPresencaDto novaPresenca = new NovaPresencaDto();
            novaPresenca.setData(participante.getDataPresenca());
            novaPresenca.setParticipanteId(participante.getId());
            novaPresenca.setEventoId(participante.getEventoId());

            presencaService.marcarPresenca(novaPresenca);

            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage("Presença marcada com sucesso"));

            return facesContext.getViewRoot().getViewId().concat("?eventoId=").concat(participante.getEventoId().toString()).concat("&faces-redirect=true");
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(cv -> {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados Incorretos ao marcar presença", cv.getMessage()));                
            });
            return null;
        }catch (ValidationException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados Incorretos ao marcar presença", e.getMessage()));
            return null;
        }

    }

}
