
package com.springboot.bbva.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bbva.app.entity.Cliente;
import com.springboot.bbva.app.entity.Usuario;
import com.springboot.bbva.app.repository.ClienteRepository;
import com.springboot.bbva.app.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/clientes/{idCliente}")
	public Cliente getCliente(@PathVariable String idCliente, @RequestBody Usuario usuario) {
		Cliente cliente = null;
		Usuario nuevoUsuario = usuarioRepository.findUsuarioByUsuario(usuario.getUsuario());
		if(nuevoUsuario != null) {
			if(nuevoUsuario.getAuth().equals(usuario.getAuth())) {
				if(nuevoUsuario.getPerfil().equals("Manager")) {
					cliente = clienteRepository.findClienteByIdCliente(idCliente);
				}
			} else {
				System.out.println("No existe en el sistema");
			}
		} else {
			System.out.println("No hay usuario");
		}
		return cliente;
	}

}
