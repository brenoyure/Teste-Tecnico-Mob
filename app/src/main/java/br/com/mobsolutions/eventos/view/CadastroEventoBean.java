package br.com.mobsolutions.eventos.view;

import java.io.Serializable;

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

	public String cadastrar() {
	    facesContext.getExternalContext().getFlash().setKeepMessages(true);
		Evento eventoCadastrado = 
				eventoService.cadastrar(novoEvento);
		facesContext.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Evento " + eventoCadastrado.getNome() + " cadastrado com sucesso", 
						"Seu evento " + eventoCadastrado.getNome() + " foi cadastrado com sucesso, Agora é possível cadastrar os participantes"));
		return "/atualizacaoEvento?eventoId=" + eventoCadastrado.getId() + "&faces-redirect=true";
	}

	/* Getters e Setters */

	public NovoEventoDto getNovoEvento() {
		return novoEvento;
	}

	public void setNovoEvento(NovoEventoDto novoEvento) {
		this.novoEvento = novoEvento;
	}

}
