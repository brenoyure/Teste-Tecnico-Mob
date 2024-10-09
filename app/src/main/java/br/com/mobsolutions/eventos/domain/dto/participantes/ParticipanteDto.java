package br.com.mobsolutions.eventos.domain.dto.participantes;

import java.util.Objects;

public class ParticipanteDto {

    private final Long id;
    private final String nome;
    private final String email;
    private final String cpf;

    private Long presencaId;

    private Long eventoId;

    public ParticipanteDto(Long id, String nome, String email, String cpf, Long presencaId, Long eventoId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.presencaId = presencaId;
        this.eventoId = eventoId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public Long getPresencaId() {
        return presencaId;
    }

    public Long getEventoId() {
        return eventoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParticipanteDto other = (ParticipanteDto) obj;
        return Objects.equals(id, other.id);
    }

    

}
