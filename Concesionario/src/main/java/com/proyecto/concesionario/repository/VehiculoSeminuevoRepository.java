package com.proyecto.concesionario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.concesionario.entity.VehiculoSeminuevo;

public interface VehiculoSeminuevoRepository extends JpaRepository<VehiculoSeminuevo, Integer> {
	Optional<VehiculoSeminuevo> findByMatricula(String matricula);

	boolean existsByMatricula(String matricula);

	List<VehiculoSeminuevo> findAllByAlquilable(boolean alquilable);

	boolean existsByAlquilable(boolean alquilable);

	List<VehiculoSeminuevo> findAllByActivo(boolean activo);

	boolean existsByActivo(boolean activo);
}
