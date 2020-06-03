package br.com.multidbprofile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.multidbprofile.model.Livro;
import br.com.multidbprofile.service.LivroService;


@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService _livroService;
	
	@GetMapping
	public List<Livro> obterLivros(){
		return _livroService.obterLivros();
	}
	
	@PostMapping
	public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
	
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(_livroService.criar(livro));
			
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
