package com.proyecto.concesionario.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class VentaDto {

	@NotNull
	private boolean cancelada;
	@NotNull
	private boolean finalizada;
	@Min(0)
	private Integer precio;
	@NotNull
	private String nombreUsuario;
	@NotNull
	private Integer idVehiculo;
	@NotNull
	private Integer idModelo;
	@NotNull
	private Integer idMotor;

	public VentaDto() {
	}

	public VentaDto(@NotNull boolean cancelada, @NotNull boolean finalizada, @Min(0) Integer precio,
			@NotNull String nombreUsuario, @NotNull Integer idVehiculo, @NotNull Integer idModelo,
			@NotNull Integer idMotor) {
		super();
		this.cancelada = cancelada;
		this.finalizada = finalizada;
		this.precio = precio;
		this.nombreUsuario = nombreUsuario;
		this.idVehiculo = idVehiculo;
		this.idModelo = idModelo;
		this.idMotor = idMotor;
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

	public Integer getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	public Integer getIdMotor() {
		return idMotor;
	}

	public void setIdMotor(Integer idMotor) {
		this.idMotor = idMotor;
	}

}