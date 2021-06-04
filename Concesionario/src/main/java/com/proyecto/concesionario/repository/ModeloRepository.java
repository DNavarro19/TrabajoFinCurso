package com.proyecto.concesionario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.concesionario.entity.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
	Optional<Modelo> findByNombre(String nombre);

	boolean existsByNombre(String nombre);
}
