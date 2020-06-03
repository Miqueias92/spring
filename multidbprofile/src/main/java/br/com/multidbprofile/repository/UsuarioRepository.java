package br.com.multidbprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multidbprofile.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
