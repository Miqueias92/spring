package br.com.flywaydb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import br.com.flywaydb.model.Livro;

@Repository
public class LivroRepository {
	
	public LivroRepository(NamedParameterJdbcTemplate template) {
		this.template = template;
	}
	
	NamedParameterJdbcTemplate template;  

	public List<Livro> findAll() {
		return template.query("select * from livro", 
				(rs, rowNum) ->
        				new Livro(
        						rs.getLong("codigo"),
        						rs.getString("titulo"),
        						rs.getString("autor"),
        						""
        ));
	}
}
