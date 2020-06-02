package br.com.multidb.multidb.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multidb.multidb.model.auth.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}