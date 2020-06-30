package br.com.mapper.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.mapper.model.GenericModel;
import br.com.mapper.model.Livro;

@Repository
public class LivroRepository extends GenericRepository{

	public LivroRepository(NamedParameterJdbcTemplate template) {
		super(template);
	}

	@Override
	protected String getElementClass() {
		return Livro.class.getSimpleName().toLowerCase() ;
	}

	@Override
	protected GenericModel getModel(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
		livro.setResultSet(rs);
		return livro;
	}
}
