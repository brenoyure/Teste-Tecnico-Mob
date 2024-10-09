package br.com.mobsolutions.eventos.domain.dto.participantes;

import java.time.LocalDate;
import java.util.Objects;

public class ParticipanteDto {

    private Long id;
    private String nome;
    private String email;
    private String cpf;

    private Long presencaId;
    private LocalDate dataPresenca;

    private Long percentualParticipacao;

    private Long eventoId;

    public ParticipanteDto() {

    }

    public ParticipanteDto(Long id, String nome, String email, String cpf, Long presencaId, LocalDate dataPresenca, Double percentualParticipacao, Long eventoId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.presencaId = presencaId;
        this.dataPresenca = dataPresenca;
        this.percentualParticipacao = Math.round(percentualParticipacao);
        this.eventoId = eventoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getPresencaId() {
        return presencaId;
    }

    public void setPresencaId(Long presencaId) {
        this.presencaId = presencaId;
    }

    public LocalDate getDataPresenca() {
        return dataPresenca;
    }

    public void setDataPresenca(LocalDate dataPresenca) {
        this.dataPresenca = dataPresenca;
    }

    public Long getPercentualParticipacao() {
        return percentualParticipacao;
    }

    public void setPercentualParticipacao(Long percentualParticipacao) {
        this.percentualParticipacao = percentualParticipacao;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
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
