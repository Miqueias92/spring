package br.com.multidbprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multidbprofile.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
