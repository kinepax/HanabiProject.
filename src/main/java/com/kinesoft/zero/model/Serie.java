package com.kinesoft.zero.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Serie {
	
	public Integer id;
	public String serie;
	public Usuario usuario;
	public LocalDateTime fecha_hora;
	public String serie_anterior;
	public String serie_nueva;
	public String operacion;
	
	
	public Serie(){

	};
	
	public Serie(Integer id, String serie, Usuario usuario, LocalDateTime fecha_hora, String serie_anterior,
			String serie_nueva, String operacion) {
		super();
		this.id = id;
		this.serie = serie;
		this.usuario = usuario;
		this.fecha_hora = fecha_hora;
		this.serie_anterior = serie_anterior;
		this.serie_nueva = serie_nueva;
		this.operacion = operacion;
	}

	public Serie(Integer id, String serie) {
		super();
		this.id = id;
		this.serie = serie;

	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public LocalDateTime getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(LocalDateTime fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	public String getSerie_anterior() {
		return serie_anterior;
	}
	public void setSerie_anterior(String serie_anterior) {
		this.serie_anterior = serie_anterior;
	}
	public String getSerie_nueva() {
		return serie_nueva;
	}
	public void setSerie_nueva(String serie_nueva) {
		this.serie_nueva = serie_nueva;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
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
		Serie other = (Serie) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return  serie ;
	}
}
