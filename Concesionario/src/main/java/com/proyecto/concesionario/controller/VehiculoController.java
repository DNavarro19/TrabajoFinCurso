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
import com.proyecto.concesionario.dto.VehiculoDto;
import com.proyecto.concesionario.entity.Vehiculo;
import com.proyecto.concesionario.service.VehiculoService;

@RestController
@RequestMapping("/vehiculo")
@CrossOrigin(origins = "http://localhost:8100")
public class VehiculoController {

	@Autowired
	VehiculoService vehiculoService;

	@GetMapping("/lista")
	public ResponseEntity<List<Vehiculo>> list() {
		List<Vehiculo> list = vehiculoService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<Vehiculo> getById(@PathVariable("id") int id) {
		if (!vehiculoService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Vehiculo vehiculo = vehiculoService.getOne(id).get();
		return new ResponseEntity(vehiculo, HttpStatus.OK);
	}

	@GetMapping("/detailname/{nombre}")
	public ResponseEntity<Vehiculo> getByNombre(@PathVariable("nombre") String nombre) {
		if (!vehiculoService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Vehiculo vehiculo = vehiculoService.getByNombre(nombre).get();
		return new ResponseEntity(vehiculo, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody VehiculoDto vehiculoDto) {
		if (StringUtils.isBlank(vehiculoDto.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (vehiculoDto.getPrecio() == null || vehiculoDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		if (vehiculoService.existsByNombre(vehiculoDto.getNombre()))
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Vehiculo vehiculo = new Vehiculo(vehiculoDto.getNombre(), vehiculoDto.getPrecio());
		vehiculoService.save(vehiculo);
		return new ResponseEntity(new Mensaje("Vehiculo creado"), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody VehiculoDto vehiculoDto) {
		if (!vehiculoService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(vehiculoDto.getNombre()))
			return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (vehiculoService.existsByNombre(vehiculoDto.getNombre())
				&& vehiculoService.getByNombre(vehiculoDto.getNombre()).get().getId() != id)
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (vehiculoDto.getPrecio() == null || vehiculoDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

		Vehiculo vehiculo = vehiculoService.getOne(id).get();
		vehiculo.setNombre(vehiculoDto.getNombre());
		vehiculo.setPrecio(vehiculoDto.getPrecio());
		vehiculoService.save(vehiculo);
		return new ResponseEntity(new Mensaje("Vehiculo actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!vehiculoService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		vehiculoService.delete(id);
		return new ResponseEntity(new Mensaje("Vehiculo eliminado"), HttpStatus.OK);
	}

}
