package com.proyecto.concesionario.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.concesionario.dto.Mensaje;
import com.proyecto.concesionario.dto.MotorDto;
import com.proyecto.concesionario.entity.Motor;
import com.proyecto.concesionario.service.MotorService;

@RestController
@RequestMapping("/motor")
@CrossOrigin(origins = "http://localhost:8100")
public class MotorController {

	@Autowired
	MotorService motorService;

	@GetMapping("/lista")
	public ResponseEntity<List<Motor>> list() {
		List<Motor> list = motorService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<Motor> getById(@PathVariable("id") int id) {
		if (!motorService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Motor motor = motorService.getOne(id).get();
		return new ResponseEntity(motor, HttpStatus.OK);
	}

	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<Motor> getByNombre(@PathVariable("nombre") String nombre) {
		if (!motorService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Motor motor = motorService.getByNombre(nombre).get();
		return new ResponseEntity(motor, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody MotorDto motorDto) {
		if (StringUtils.isBlank(motorDto.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (motorDto.getPrecio() == null || motorDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		if (motorService.existsByNombre(motorDto.getNombre()))
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (motorDto.getCaballos() == null || motorDto.getCaballos() < 65)
			return new ResponseEntity(new Mensaje("Los caballos deben ser mayor que 65"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(motorDto.getCombustible()))
			return new ResponseEntity(new Mensaje("El tipo de combustible es obligatorio"), HttpStatus.BAD_REQUEST);
		Motor motor = new Motor(motorDto.getNombre(), motorDto.getCaballos(), motorDto.getCombustible(),
				motorDto.getPrecio());
		motorService.save(motor);
		return new ResponseEntity(new Mensaje("Motor creado"), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody MotorDto motorDto) {
		if (!motorService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(motorDto.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (motorService.existsByNombre(motorDto.getNombre())
				&& motorService.getByNombre(motorDto.getNombre()).get().getId() != id)
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (motorDto.getCaballos() == null || motorDto.getCaballos() < 65)
			return new ResponseEntity(new Mensaje("Los caballos deben ser mayor que 65"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(motorDto.getCombustible()))
			return new ResponseEntity(new Mensaje("El tipo de combustible es obligatorio"), HttpStatus.BAD_REQUEST);
		if (motorDto.getPrecio() == null || motorDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

		Motor motor = motorService.getOne(id).get();
		motor.setNombre(motorDto.getNombre());
		motor.setCaballos(motor.getCaballos());
		motor.setCombustible(motor.getCombustible());
		motor.setPrecio(motorDto.getPrecio());
		motorService.save(motor);
		return new ResponseEntity(new Mensaje("Motor actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!motorService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		motorService.delete(id);
		return new ResponseEntity(new Mensaje("Motor eliminado"), HttpStatus.OK);
	}

}
