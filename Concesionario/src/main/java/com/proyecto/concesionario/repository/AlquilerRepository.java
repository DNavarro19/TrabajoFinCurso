package com.proyecto.concesionario.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.concesionario.entity.Alquiler;
import com.proyecto.concesionario.security.entity.Usuario;

public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {
	Optional<Alquiler> findByFechaInicio(Date fechaInicio);

	boolean existsByFechaInicio(Date fechaInicio);

	Optional<Alquiler> findByFechaFin(Date fechaFin);

	boolean existsByFechaFin(Date fechaFin);

	List<Alquiler> findAllByUsuario(Usuario usuario);

	boolean existsByUsuario(Usuario usuario);
}
