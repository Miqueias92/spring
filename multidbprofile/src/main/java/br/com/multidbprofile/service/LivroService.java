package br.com.multidbprofile.service;

import java.util.List;

import br.com.multidbprofile.model.Livro;

public interface LivroService {
	public List<Livro> obterLivros();
	public Livro criar(Livro livro);
}
