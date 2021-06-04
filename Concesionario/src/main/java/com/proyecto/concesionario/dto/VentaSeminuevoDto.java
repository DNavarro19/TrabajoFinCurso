package com.proyecto.concesionario.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class VentaSeminuevoDto {

	@NotNull
	private boolean cancelada;
	@NotNull
	private boolean finalizada;
	@Min(0)
	private Integer precio;
	@NotNull
	private String nombreUsuario;
	@NotNull
	private Integer idVehiculoSeminuevo;

	public VentaSeminuevoDto() {
	}

	public VentaSeminuevoDto(@NotNull boolean cancelada, @NotNull boolean finalizada, @Min(0) Integer precio,
			@NotNull String nombreUsuario, @NotNull Integer idVehiculoSeminuevo) {
		super();
		this.cancelada = cancelada;
		this.finalizada = finalizada;
		this.precio = precio;
		this.nombreUsuario = nombreUsuario;
		this.idVehiculoSeminuevo = idVehiculoSeminuevo;
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