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
import com.proyecto.concesionario.dto.VentaDto;
import com.proyecto.concesionario.entity.Modelo;
import com.proyecto.concesionario.entity.Motor;
import com.proyecto.concesionario.entity.Vehiculo;
import com.proyecto.concesionario.entity.Venta;
import com.proyecto.concesionario.security.entity.Usuario;
import com.proyecto.concesionario.security.service.UsuarioService;
import com.proyecto.concesionario.service.ModeloService;
import com.proyecto.concesionario.service.MotorService;
import com.proyecto.concesionario.service.VehiculoService;
import com.proyecto.concesionario.service.VentaService;

@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = "http://localhost:8100")
public class VentaController {

	@Autowired
	VentaService ventaService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	VehiculoService vehiculoService;

	@Autowired
	ModeloService modeloService;

	@Autowired
	MotorService motorService;

	@GetMapping("/lista")
	public ResponseEntity<List<Venta>> list() {
		List<Venta> list = ventaService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<Venta> getById(@PathVariable("id") int id) {
		if (!ventaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		Venta venta = ventaService.getOne(id).get();
		return new ResponseEntity(venta, HttpStatus.OK);
	}

	@GetMapping("/listByUsuario/{nombreUsuario}")
	public ResponseEntity<Venta> getByIdUsuario(@PathVariable("nombreUsuario") String nombreUsuario) {
		if (!usuarioService.existsByNombreUsuario(nombreUsuario))
			return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.NOT_FOUND);
		Usuario user = usuarioService.getByNombreUsuario(nombreUsuario).get();
		if (!ventaService.existsByUsuario(user))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		List<Venta> list = ventaService.listByUsuario(user);
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody VentaDto ventaDto) {
		if (ventaDto.getIdVehiculo() == null || !vehiculoService.existsById(ventaDto.getIdVehiculo()))
			return new ResponseEntity(new Mensaje("El vehiculo no existe"), HttpStatus.BAD_REQUEST);
		if (ventaDto.getIdModelo() == null || !modeloService.existsById(ventaDto.getIdModelo()))
			return new ResponseEntity(new Mensaje("El modelo no existe"), HttpStatus.BAD_REQUEST);
		if (ventaDto.getIdMotor() == null || !motorService.existsById(ventaDto.getIdMotor()))
			return new ResponseEntity(new Mensaje("El motor no existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(ventaDto.getNombreUsuario())
				|| !usuarioService.existsByNombreUsuario(ventaDto.getNombreUsuario()))
			return new ResponseEntity(new Mensaje("El usuario no existe"), HttpStatus.BAD_REQUEST);
		if (ventaDto.getPrecio() == null || ventaDto.getPrecio() < 0)
			return new ResponseEntity(new Mensaje("El precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
		Usuario user = usuarioService.getByNombreUsuario(ventaDto.getNombreUsuario()).get();
		Vehiculo vehiculo = vehiculoService.getOne(ventaDto.getIdVehiculo()).get();
		Modelo modelo = modeloService.getOne(ventaDto.getIdModelo()).get();
		Motor motor = motorService.getOne(ventaDto.getIdMotor()).get();
		Venta venta = new Venta(ventaDto.isCancelada(), ventaDto.isFinalizada(), ventaDto.getPrecio());
		venta.setUsuario(user);
		venta.setVehiculo(vehiculo);
		venta.setModelo(modelo);
		venta.setMotor(motor);
		ventaService.save(venta);
		return new ResponseEntity(new Mensaje("Venta creada"), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody VentaDto ventaDto) {
		Venta venta = ventaService.getOne(id).get();
		if (venta.isCancelada())
			return new ResponseEntity(new Mensaje("Esta venta ya ha sido cancelada"), HttpStatus.BAD_REQUEST);
		if (venta.isFinalizada())
			return new ResponseEntity(new Mensaje("Esta venta ya ha sido finalizada"), HttpStatus.BAD_REQUEST);

		venta.setCancelada(ventaDto.isCancelada());
		venta.setFinalizada(ventaDto.isFinalizada());
		venta.setPrecio(ventaDto.getPrecio());
		ventaService.save(venta);
		return new ResponseEntity(new Mensaje("Venta actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!ventaService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		ventaService.delete(id);
		return new ResponseEntity(new Mensaje("Venta eliminada"), HttpStatus.OK);
	}

}
