package com.proyecto.concesionario.dto;

import java.sql.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AlquilerDto {

	@FutureOrPresent
	private Date fechaInicio;
	@Future
	private Date fechaFin;
	@NotNull
	private boolean cancelado;
	@NotNull
	private boolean activo;
	@Min(0)
	private Integer precio;
	@NotNull
	private String nombreUsuario;
	@NotNull
	private Integer idVehiculoSeminuevo;

	public AlquilerDto() {
	}

	public AlquilerDto(@FutureOrPresent Date fechaInicio, @Future Date fechaFin, @NotNull boolean cancelado,
			@NotNull boolean activo, @Min(0) Integer precio, @NotNull String nombreUsuario,
			@NotNull Integer idVehiculoSeminuevo) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cancelado = cancelado;
		this.activo = activo;
		this.precio = precio;
		this.nombreUsuario = nombreUsuario;
		this.idVehiculoSeminuevo = idVehiculoSeminuevo;
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

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Integer getIdVehiculoSeminuevo() {
		return idVehiculoSeminuevo;
	}

	public void setIdVehiculoSeminuevo(Integer idVehiculoSeminuevo) {
		this.idVehiculoSeminuevo = idVehiculoSeminuevo;
	}

}