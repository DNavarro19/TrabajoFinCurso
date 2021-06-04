package com.proyecto.concesionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.concesionario.entity.Motor;
import com.proyecto.concesionario.repository.MotorRepository;

@Service
@Transactional
public class MotorService {

	@Autowired
	MotorRepository motorRepository;

	public List<Motor> list() {
		return motorRepository.findAll();
	}

	public Optional<Motor> getOne(int id) {
		return motorRepository.findById(id);
	}

	public Optional<Motor> getByNombre(String nombre) {
		return motorRepository.findByNombre(nombre);
	}

	public void save(Motor motor) {
		motorRepository.save(motor);
	}

	public void delete(int id) {
		motorRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return motorRepository.existsById(id);
	}

	public boolean existsByNombre(String nombre) {
		return motorRepository.existsByNombre(nombre);
	}
}
