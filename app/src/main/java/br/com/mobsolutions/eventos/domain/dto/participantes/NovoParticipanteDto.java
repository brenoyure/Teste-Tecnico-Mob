package br.com.mobsolutions.eventos.domain.dto.participantes;

import java.io.Serializable;

import jakarta.enterprise.context.Dependent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Dependent
public class NovoParticipanteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Nome do Participante Obrigatório")
    private String nome;

    @NotBlank(message = "CPF do Participante Obrigatório")
    @Size(min = 14, max = 14, message = "CPF do Participante deve conter 11 caractéres numéricos, dois hífens e um ponto")
    private String cpf;

    @NotBlank(message = "E-mail do Participante Obrigatório")
    @Email(message = "E-mail do Participante está em um formato inválido")
    private String email;

    @NotNull @Positive
    private Long eventoId;

    public NovoParticipanteDto() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }

}
