
package com.springboot.bbva.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.bbva.app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

	@Query("SELECT c FROM Cliente c WHERE idCliente = ?1")
	public Optional<Cliente> findById(String idCliente);

}
