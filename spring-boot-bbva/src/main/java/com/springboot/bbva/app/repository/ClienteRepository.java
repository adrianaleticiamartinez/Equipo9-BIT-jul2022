
package com.springboot.bbva.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.bbva.app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	// MANAGER
	@Query(value = "SELECT * FROM clientes WHERE id_cliente = ?1", nativeQuery = true)
	public Cliente findClienteByIdCliente(String idCliente);

}
