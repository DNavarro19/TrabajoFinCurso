package com.proyecto.concesionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.concesionario.entity.Venta;
import com.proyecto.concesionario.security.entity.Usuario;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
	List<Venta> findAllByUsuario(Usuario usuario);

	boolean existsByUsuario(Usuario usuario);

}
