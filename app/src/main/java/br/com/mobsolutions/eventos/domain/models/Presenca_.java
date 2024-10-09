package br.com.mobsolutions.eventos.domain.models;

import java.time.LocalDate;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Presenca.class)
public abstract class Presenca_ {

    public static volatile SingularAttribute<Presenca, Long> id;
    public static volatile SingularAttribute<Presenca, LocalDate> data;
    public static volatile SingularAttribute<Presenca, Participante> participante;

}