package br.com.mobsolutions.eventos.domain.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Participante.class)
public abstract class Participante_ {

    public static volatile SingularAttribute<Participante, Long> id;
    public static volatile SingularAttribute<Participante, String> nome;
    public static volatile SingularAttribute<Participante, String> email;
    public static volatile SingularAttribute<Participante, String> cpf;
    public static volatile SingularAttribute<Participante, Evento> evento;

}
