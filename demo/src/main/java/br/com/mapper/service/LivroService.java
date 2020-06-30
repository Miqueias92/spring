package br.com.mapper.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.mapper.model.Livro;
import br.com.mapper.repository.LivroRepository;

@Service
public class LivroService {

	private LivroRepository repository;
	
	@Qualifier("jdbcTemplate1")
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public LivroService(LivroRepository repository) {
		this.repository = repository;
	}
	
	public Iterable<Livro> findAll() { 		
		List<Livro> livros = new ArrayList<Livro>();
		repository.findAll().forEach(l -> livros.add((Livro)l));
		
		try {
			String banco = this.jdbcTemplate.getDataSource().getConnection().getCatalog();
			System.out.println("banco: " + banco);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return livros;
	}
	
	public Livro save(Livro livro) throws IllegalArgumentException {
		return (Livro) repository.save(livro);
	}

	public boolean update(Long id, Livro livro) throws IllegalArgumentException {		
		livro.setId(id);
		return repository.update(livro);
	}

	public boolean delete(Long id) {	
		return repository.deleteById(id);
	}
}
