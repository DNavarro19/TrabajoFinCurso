package com.proyecto.concesionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.concesionario.entity.VentaSeminuevo;
import com.proyecto.concesionario.security.entity.Usuario;

public interface VentaSeminuevoRepository extends JpaRepository<VentaSeminuevo, Integer> {
	List<VentaSeminuevo> findAllByUsuario(Usuario usuario);

	boolean existsByUsuario(Usuario usuario);
}
