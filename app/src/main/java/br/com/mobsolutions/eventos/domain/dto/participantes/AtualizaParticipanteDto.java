package br.com.mobsolutions.eventos.domain.dto.participantes;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AtualizaParticipanteDto extends NovoParticipanteDto {

    private static final long serialVersionUID = 1L;

    @NotNull @Positive
    private Long participanteId;

    public Long getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    public AtualizaParticipanteDto(ParticipanteDto participanteDto) {
        super(participanteDto.getNome(), participanteDto.getCpf(), participanteDto.getEmail(), participanteDto.getEventoId());
        this.participanteId = participanteDto.getId();
    }

}
