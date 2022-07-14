
package com.springboot.bbva.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.bbva.app.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	@Query(value = "SELECT * FROM usuarios WHERE usuario = ?1", nativeQuery = true)
	public Usuario findUsuarioByUsuario(String usuario);

}
