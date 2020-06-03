package br.com.multidbprofile.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.multidbprofile.model.Livro;
import br.com.multidbprofile.model.Usuario;
import br.com.multidbprofile.repository.LivroRepository;
import br.com.multidbprofile.repository.UsuarioRepository;
import br.com.multidbprofile.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService{
	
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
