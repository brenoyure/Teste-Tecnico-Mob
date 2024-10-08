package br.com.mobsolutions.eventos.domain.models;

import java.time.LocalDate;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Evento.class)
public abstract class Evento_ {

	public static SingularAttribute<Evento, Long> id;
	public static SingularAttribute<Evento, String> nome;
	public static SingularAttribute<Evento, LocalDate> dataInicio;
	public static SingularAttribute<Evento, LocalDate> dataFim;

}
