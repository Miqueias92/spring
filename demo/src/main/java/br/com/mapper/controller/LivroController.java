package br.com.mapper.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mapper.model.Livro;
import br.com.mapper.service.LivroService;

@RestController
@RequestMapping()
public class LivroController {

	private LivroService service;
	
	public LivroController(LivroService service) {
		this.service = service;
	}
	
	
	/**
	 * Get the list of all location
	 * 
	 * @return
	 */
	@RequestMapping(path = "/api/livro", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Livro>> list() {
		
		Iterable<Livro> livros = (List<Livro>) service.findAll();
		return ResponseEntity.ok(livros);
	}

	/**
	 * To create an location
	 * 
	 * @param location
	 * @return
	 */
	@RequestMapping(path = "/api/livro", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Livro livro) {
		try {
			Livro newLivro = service.save(livro);
			if (newLivro != null)
				return new ResponseEntity<>(newLivro, HttpStatus.CREATED);
			else
				return new ResponseEntity<>("Already created", HttpStatus.CONFLICT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * To update a location
	 * 
	 * @param location
	 * @return
	 */
	@RequestMapping(path = "/api/livro/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Livro livro, @PathVariable Long id) {

		try {
			if (service.update(id, livro)) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Delete the location by its id
	 * 
	 * @param title
	 */
	@RequestMapping(path = "/api/livro/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
