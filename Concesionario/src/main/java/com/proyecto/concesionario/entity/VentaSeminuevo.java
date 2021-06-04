package com.proyecto.concesionario.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.proyecto.concesionario.security.entity.Usuario;

@Entity
public class VentaSeminuevo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean cancelada;
	private boolean finalizada;
	private int precio;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	@ManyToOne(optional = false)
	@JoinColumn(name = "vehiculo_seminuevo_id", referencedColumnName = "id")
	private VehiculoSeminuevo vehiculoSeminuevo;

	public VentaSeminuevo() {
		super();
	}

	public VentaSeminuevo(boolean cancelada, boolean finalizada, int precio) {
		super();
		this.cancelada = cancelada;
		this.finalizada = finalizada;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public VehiculoSeminuevo getVehiculoSeminuevo() {
		return vehiculoSeminuevo;
	}

	public void setVehiculoSeminuevo(VehiculoSeminuevo vehiculoSeminuevo) {
		this.vehiculoSeminuevo = vehiculoSeminuevo;
	}

}
