package br.com.mobsolutions.eventos.domain.dto.evento;

import java.io.Serializable;

import br.com.mobsolutions.eventos.domain.models.Evento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AtualizacaoEventoDto extends NovoEventoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull @Positive
    private Long id;

    public AtualizacaoEventoDto() {

    }

    public AtualizacaoEventoDto(Evento evento) {
        super(evento.getNome(), evento.getDataInicio(), evento.getDataFim());
        this.id = evento.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
