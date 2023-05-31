package com.kinesoft.zero.model;

import java.util.Objects;

public class Usuario_Mesas {
	public Integer 		id;
	public Usuario  user;
	public Mesa mesa;
	
	
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
		Usuario_Mesas other = (Usuario_Mesas) obj;
		return Objects.equals(id, other.id);
	}
	
	public Usuario_Mesas(Integer id, Usuario user, Mesa mesa) {
		super();
		this.id = id;
		this.user = user;
		this.mesa = mesa;
	}
	
	public Usuario_Mesas() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public String getStrNomMesa() {
		return this.mesa.getNombre();
		
		
	}
	
	

}
