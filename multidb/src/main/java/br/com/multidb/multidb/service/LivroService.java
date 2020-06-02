package br.com.multidb.multidb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.multidb.multidb.model.app.Livro;

public interface LivroService {
	
	public List<Livro> obterLivros();
	public Livro criar(Livro livro);
}
