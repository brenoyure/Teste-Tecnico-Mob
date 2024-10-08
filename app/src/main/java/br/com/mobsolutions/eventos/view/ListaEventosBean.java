package br.com.mobsolutions.eventos.view;

import java.io.Serializable;
import java.util.List;

import br.com.mobsolutions.eventos.domain.dto.evento.PesquisaEventoNoDataTableDto;
import br.com.mobsolutions.eventos.domain.models.Evento;
import br.com.mobsolutions.eventos.repositories.EventoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named @ViewScoped
public class ListaEventosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	private List<Evento> eventos;

	@Inject
	private PesquisaEventoNoDataTableDto dadosPesquisa;

	@Inject
	private EventoRepository eventoRepository;

	@PostConstruct
	void init() {
		eventos = eventoRepository.findAll();
	}

	public void pesquisar() {
		eventos = 
				eventoRepository.findByNomeLikeAndDataInicioAndDataFim(
						dadosPesquisa.getNome(), 
						dadosPesquisa.getDataInicio(), 
						dadosPesquisa.getDataFim());
	}

	@Transactional
	public String excluirEvento(Long id) {
	    facesContext.getExternalContext().getFlash().setKeepMessages(true);

	    eventoRepository.deleteById(id);
	    facesContext.addMessage(null, 
	            new FacesMessage("Evento excluído com sucesso"));

	    return facesContext.getViewRoot().getViewId().concat("?faces-redirect=true");
	}

	/*Getters e Setters*/
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public PesquisaEventoNoDataTableDto getDadosPesquisa() {
		return dadosPesquisa;
	}

	public void setDadosPesquisa(PesquisaEventoNoDataTableDto dadosPesquisa) {
		this.dadosPesquisa = dadosPesquisa;
	}

}
