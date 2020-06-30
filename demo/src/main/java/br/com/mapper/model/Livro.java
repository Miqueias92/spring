package br.com.mapper.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Livro extends GenericModel{

	private String titulo;
	private String tema;
	private String autor;
	
	@Override
	public SqlParameterSource getParams() {
		
		return new MapSqlParameterSource()
				.addValue("titulo", this.getTitulo())
				.addValue("autor", this.getAutor())
				.addValue("tema", this.getTema());
	}

	@Override
	public void setResultSet(ResultSet rs) throws SQLException {
		this.setId(rs.getLong("id"));
		this.setTitulo(rs.getString("titulo"));
		this.setTema(rs.getString("tema"));
		this.setAutor(rs.getString("autor"));
	}
}
