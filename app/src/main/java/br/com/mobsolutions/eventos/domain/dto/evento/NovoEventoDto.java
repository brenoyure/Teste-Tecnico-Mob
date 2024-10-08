package br.com.mobsolutions.eventos.domain.dto.evento;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.enterprise.context.Dependent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Dependent
public class NovoEventoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Nome do Evento Obrigatório")
	private String nome;

	@NotNull(message = "Data Inicial do Evento Obrigatória")
	private LocalDate dataInicial;

	@NotNull(message = "Data Final do Evento Obrigatória")
	private LocalDate dataFinal;

	public NovoEventoDto() {

	}

	public NovoEventoDto(String nome, LocalDate dataInicial, LocalDate dataFinal) {
        this.nome = nome;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

}
