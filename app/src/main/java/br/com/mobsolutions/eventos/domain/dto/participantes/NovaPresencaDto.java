package br.com.mobsolutions.eventos.domain.dto.participantes;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.enterprise.context.Dependent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Dependent
public class NovaPresencaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Data que o Participante irá ao avento Obrigatória")
    private LocalDate data;

    @NotNull
    @Positive
    private Long participanteId;

    @NotNull
    @Positive
    private Long eventoId;

    public NovaPresencaDto() {

    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

}
