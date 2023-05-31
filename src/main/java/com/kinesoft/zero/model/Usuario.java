package com.kinesoft.zero.model;

import java.util.Objects;

public class Usuario {
	public Integer 		id;
	public String 		nombre;
	public String 		usuario;
	public String 		pass;
	
	public Usuario() {
		
	}
	
	public Usuario(Integer id, String nombre, String usuario, String pass) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
		this.pass = pass;
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
		Usuario other = (Usuario) obj;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return  usuario ;

	}
}
