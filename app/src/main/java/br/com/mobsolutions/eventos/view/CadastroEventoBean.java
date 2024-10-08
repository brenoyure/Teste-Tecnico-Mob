package br.com.mobsolutions.eventos.view;

import java.io.Serializable;

import org.primefaces.component.menuitem.UIMenuItem;

import br.com.mobsolutions.eventos.domain.dto.evento.NovoEventoDto;
import br.com.mobsolutions.eventos.domain.models.Evento;
import br.com.mobsolutions.eventos.domain.services.EventoService;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @ViewScoped
public class CadastroEventoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private EventoService eventoService;

	@Inject
	private NovoEventoDto novoEvento;

	private UIMenuItem menuButtonParticipantes;

	private boolean telaAtualParticipantes = false;

	public void cadastrar() {
		Evento eventoCadastrado = 
				eventoService.cadastrar(novoEvento);
		menuButtonParticipantes.setDisabled(false);

		facesContext.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Evento " + eventoCadastrado.getNome() + " cadastrado com sucesso", 
						"Seu evento de id " + eventoCadastrado.getId() + " foi cadastrado com sucesso, Agora é possível cadastrar os participantes"));

	}

    public void alternarParaCadastro() {
        telaAtualParticipantes = false;
    }

	public void alternarParaParticipantes() {
	    telaAtualParticipantes = true;
	}

	/* Getters e Setters */

	public NovoEventoDto getNovoEvento() {
		return novoEvento;
	}

	public void setNovoEvento(NovoEventoDto novoEvento) {
		this.novoEvento = novoEvento;
	}

    public boolean isTelaAtualParticipantes() {
        return telaAtualParticipantes;
    }

    public void setTelaAtualParticipantes(boolean telaAtualParticipantes) {
        this.telaAtualParticipantes = telaAtualParticipantes;
    }

    public UIMenuItem getMenuButtonParticipantes() {
        return menuButtonParticipantes;
    }

    public void setMenuButtonParticipantes(UIMenuItem menuButtonParticipantes) {
        this.menuButtonParticipantes = menuButtonParticipantes;
    }

}
