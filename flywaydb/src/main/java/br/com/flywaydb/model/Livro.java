package br.com.flywaydb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Livro {
	
	private Long codigo;
	private String titulo;
	private String autor;
	private String tema;
	
	public Livro(Long codigo, String titulo, String autor, String tema) {
		this.setCodigo(codigo);
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setTema(tema);
	}
}
