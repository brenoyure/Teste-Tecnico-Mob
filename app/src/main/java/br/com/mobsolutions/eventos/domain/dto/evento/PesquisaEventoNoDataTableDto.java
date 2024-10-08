package br.com.mobsolutions.eventos.domain.dto.evento;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.enterprise.context.Dependent;

@Dependent
public class PesquisaEventoNoDataTableDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private LocalDate dataInicio;
	private LocalDate dataFim;

	public PesquisaEventoNoDataTableDto() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

}
