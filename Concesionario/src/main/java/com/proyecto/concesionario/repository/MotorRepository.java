package com.proyecto.concesionario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.concesionario.entity.Motor;

public interface MotorRepository extends JpaRepository<Motor, Integer> {
	Optional<Motor> findByNombre(String nombre);

	boolean existsByNombre(String nombre);
}
