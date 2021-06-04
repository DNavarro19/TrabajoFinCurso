package com.proyecto.concesionario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.concesionario.entity.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
	Optional<Vehiculo> findByNombre(String nombre);

	boolean existsByNombre(String nombre);
}
