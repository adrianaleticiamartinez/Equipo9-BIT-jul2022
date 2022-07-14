
package com.springboot.bbva.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bbva.app.entity.Cliente;
import com.springboot.bbva.app.entity.Usuario;
import com.springboot.bbva.app.repository.ClienteRepository;
import com.springboot.bbva.app.repository.UsuarioRepository;
import com.springboot.bbva.app.response.Restringido;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/clientes/{idCliente}")
	public ResponseEntity<?> getCliente(@PathVariable String idCliente, @RequestBody Usuario usuario) {
		Cliente cliente = null;
		Usuario nuevoUsuario = usuarioRepository.findUsuarioByUsuario(usuario.getUsuario());
		HashMap<String, Object> respuesta = new HashMap<>();
		if(nuevoUsuario != null) {
			if(nuevoUsuario.getAuth().equals(usuario.getAuth())) {
				if(nuevoUsuario.getPerfil().equals("Manager")) {
					cliente = clienteRepository.findClienteByIdCliente(idCliente);
					if(cliente != null) {
						return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
					} else {
						respuesta.put("mensaje", "El cliente no existe!");
						return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
					}
				} else if(nuevoUsuario.getPerfil().equals("Restringido")) {
					cliente = clienteRepository.findClienteByIdCliente(idCliente);
					if(cliente != null) {
						Restringido restringido = new Restringido();
						restringido.setIdCliente(cliente.getIdCliente());
						restringido.setNombre(cliente.getNombre());
						restringido.setCuenta(cliente.getCuenta());
						restringido.setSegmento(cliente.getSegmento());
						restringido.setSexo(cliente.getSexo());
						return new ResponseEntity<Restringido>(restringido, HttpStatus.OK);
					} else {
						respuesta.put("mensaje", "El cliente no existe");
						return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
					}
				} else if(nuevoUsuario.getPerfil().equals("Validador")) {
					// Aplica logica de validador
				}
			} else {
				respuesta.put("mensaje", "El usuario o contraseña son incorrectos");
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
			}
		} else {
			respuesta.put("mensaje", "El usuario no existe!");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}
		return null;
	}

}
