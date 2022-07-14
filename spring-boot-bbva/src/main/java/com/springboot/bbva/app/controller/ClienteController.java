
package com.springboot.bbva.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bbva.app.entity.Cliente;
import com.springboot.bbva.app.repository.ClienteRepository;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/clientes/{id}")
	public Cliente getCliente(@PathVariable String idCliente) {
		Optional<Cliente> optCliente = clienteRepository.findById(idCliente);
		Cliente cliente = optCliente.get();
		return cliente;
	}

}
