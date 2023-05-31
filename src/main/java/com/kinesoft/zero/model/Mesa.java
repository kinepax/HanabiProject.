package com.kinesoft.zero.model;

import java.util.Objects;

public  class Mesa {
	public Integer 		id;
	public String 		nombre;
	
	public Mesa() {
		
	}
	
	public Mesa(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
		Mesa other = (Mesa) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "" + nombre + "";
	}
}
