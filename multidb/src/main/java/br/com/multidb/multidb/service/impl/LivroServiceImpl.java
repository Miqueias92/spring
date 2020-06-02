package br.com.multidb.multidb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.multidb.multidb.model.app.Livro;
import br.com.multidb.multidb.model.auth.Usuario;
import br.com.multidb.multidb.repository.app.LivroRepository;
import br.com.multidb.multidb.repository.auth.UsuarioRepository;
import br.com.multidb.multidb.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository _livroRepository;
	
	@Autowired
	private UsuarioRepository _usuarioRepository;
	
	@Override
	public List<Livro> obterLivros() {
		return _livroRepository.findAll();
	}

	@Override
	public Livro criar(Livro livro) {
		
		Usuario usuario = _usuarioRepository
							.findById(livro.getCodigoUsuario())
							.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		
		livro.setNomeUsuario(usuario.getNome());

		return _livroRepository.save(livro);
	}
}
