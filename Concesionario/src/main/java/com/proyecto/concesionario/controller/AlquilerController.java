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

import com.proyecto.concesionario.dto.AlquilerDto;
import com.proyecto.concesionario.dto.Mensaje;
import com.proyecto.concesionario.entity.Alquiler;
import com.proyecto.concesionario.entity.VehiculoSeminuevo;
import com.proyecto.concesionario.security.entity.Usuario;
import com.proyecto.concesionario.security.service.UsuarioService;
import com.proyecto.concesionario.service.AlquilerService;
import com.proyecto.concesionario.service.VehiculoSeminuevoService;

@RestController
@RequestMapping("/alquiler")
@CrossOrigin(origins = "http://localhost:8100")
public class AlquilerController {

	@Autowired
	AlquilerService alquilerService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	VehiculoSeminuevoService vehiculoSeminuevoService;

	@GetMapping("/lista")
	public ResponseEntity<List<Alquiler>> list() {
		List<Alquiler> list = alquilerService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/listByUsuario/{nombreUsuario}")
	public ResponseEntity<Alquiler> listByIdUsuario(@PathVariable("nombreUsuario") String nombreUsuario) {
		if (!usuarioService.existsByNombreUsuario(nombreUsuario))
			return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);
		Usuario user = usuarioService.getByNombreUsuario(nombreUsuario).get();
		if (!alquilerService.existsByUsuario(user))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		List<Alquiler> list = alquilerService.listByUsuario(user);
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<Alquiler> getById(@PathVariable("id") int id) {
		if (!alquilerService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Alquiler alquiler = alquilerService.getOne(id).get();
		return new ResponseEntity(alquiler, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody AlquilerDto alquilerDto) {
		if (alquilerDto.getFechaInicio() == null)
			return new ResponseEntity(new Mensaje("La fecha inicio es obligatoria"), HttpStatus.BAD_REQUEST);
		if (alquilerDto.getFechaFin() == null)
			return new ResponseEntity(new Mensaje("La fecha inicio es obligatoria"), HttpStatus.BAD_REQUEST);
		if (alquilerDto.getPrecio() == null || alquilerDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(alquilerDto.getNombreUsuario())
				|| !usuarioService.existsByNombreUsuario(alquilerDto.getNombreUsuario()))
			return new ResponseEntity(new Mensaje("Debe ser un id de usuario valido"), HttpStatus.BAD_REQUEST);
		if (alquilerDto.getIdVehiculoSeminuevo() == null
				|| !vehiculoSeminuevoService.existsById(alquilerDto.getIdVehiculoSeminuevo()))
			return new ResponseEntity(new Mensaje("Debe ser un id de vehiculo seminuevo valido"),
					HttpStatus.BAD_REQUEST);
		VehiculoSeminuevo vehiculo = vehiculoSeminuevoService.getOne(alquilerDto.getIdVehiculoSeminuevo()).get();
		if (!vehiculo.isActivo() || !vehiculo.isAlquilable() || vehiculo.isAlquilado())
			return new ResponseEntity(new Mensaje("Debe de ser un vehiculo valido"), HttpStatus.BAD_REQUEST);
		Alquiler alquiler = new Alquiler(alquilerDto.getFechaInicio(), alquilerDto.getFechaFin(),
				alquilerDto.isCancelado(), alquilerDto.isActivo(), alquilerDto.getPrecio());
		Usuario user = usuarioService.getByNombreUsuario(alquilerDto.getNombreUsuario()).get();
		alquiler.setUsuario(user);
		alquiler.setVehiculoSeminuevo(vehiculo);
		vehiculo.setAlquilado(true);
		vehiculoSeminuevoService.save(vehiculo);
		alquilerService.save(alquiler);
		return new ResponseEntity(new Mensaje("Alquiler creado"), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody AlquilerDto alquilerDto) {
		if (!alquilerService.existsById(id))
			return new ResponseEntity(new Mensaje("El alquiler no existe"), HttpStatus.NOT_FOUND);
		Alquiler alquiler = alquilerService.getOne(id).get();
		alquiler.setActivo(alquilerDto.isActivo());
		alquiler.setCancelado(alquilerDto.isCancelado());
		if (!alquiler.isActivo()) {
			VehiculoSeminuevo vehiculo = vehiculoSeminuevoService.getOne(alquiler.getVehiculoSeminuevo().getId()).get();
			vehiculo.setAlquilado(false);
			vehiculoSeminuevoService.save(vehiculo);
		}
		alquilerService.save(alquiler);
		return new ResponseEntity(new Mensaje("Alquiler actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!alquilerService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Alquiler alquiler = alquilerService.getOne(id).get();
		VehiculoSeminuevo vehiculo = vehiculoSeminuevoService.getOne(alquiler.getVehiculoSeminuevo().getId()).get();
		if (alquiler.isActivo()) {
			vehiculo.setAlquilado(false);
			vehiculoSeminuevoService.save(vehiculo);
		}
		alquilerService.delete(id);
		return new ResponseEntity(new Mensaje("Alquiler eliminado"), HttpStatus.OK);
	}

}
