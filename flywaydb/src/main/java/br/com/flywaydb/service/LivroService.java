package br.com.flywaydb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flywaydb.model.Livro;
import br.com.flywaydb.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository _livroRepository;
		
	public List<Livro> obterLivros() {
		return _livroRepository.findAll();
	}
}
