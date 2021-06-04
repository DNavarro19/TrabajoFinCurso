package com.proyecto.concesionario.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.proyecto.concesionario.security.entity.Usuario;

@Entity
public class Alquiler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlquiler;
	private Date fechaInicio;
	private Date fechaFin;
	private boolean cancelado;
	private boolean activo;
	private float precio;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario usuario;
	@ManyToOne(optional = false)
	@JoinColumn(name = "vehiculo_seminuevo_id", referencedColumnName = "id")
	private VehiculoSeminuevo vehiculoSeminuevo;

	public Alquiler() {
	}

	public Alquiler(Date fechaInicio, Date fechaFin, boolean cancelado, boolean activo, float precio) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cancelado = cancelado;
		this.activo = activo;
		this.precio = precio;
	}

	public int getIdAlquiler() {
		return idAlquiler;
	}

	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
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
