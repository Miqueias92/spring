package br.com.multidb.multidb.repository.app;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.multidb.multidb.model.app.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
