package br.com.mapper.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import br.com.mapper.model.GenericModel;

public abstract class GenericRepository extends DataBaseGenericRepository {
	
	@Qualifier("jdbcTemplate1")
	NamedParameterJdbcTemplate template;
	
	public GenericRepository(NamedParameterJdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public Iterable<GenericModel> findAll() {
		
		return template.query(
				   "select * from " + getElementClass(),
				  (rs, rowNum) -> getModel(rs));
	}

	@Override
	public Optional<GenericModel> findById(Long id) {
		

		try {
			
			return template.queryForObject(
					"select * from " + getElementClass()+ " where id = :id", 
					new MapSqlParameterSource("id", id), 
					(rs, rowNum) -> Optional.of(getModel(rs)));
			
		} catch (EmptyResultDataAccessException e) {
			return Optional.ofNullable(null);
		}
	}
	
	@Override
	public GenericModel save(GenericModel genericModel) throws IllegalArgumentException {
		
		try {
			
			String[] columnNames = new String[] {"id"};
			GeneratedKeyHolder holder = new GeneratedKeyHolder();
			
			String sql = "insert into livro (titulo, autor, tema) values (:titulo, :autor, :tema); ";
			template.update(sql, genericModel.getParams(), holder, columnNames);
			
			Map<String,Object> keys = holder.getKeys();
            
			genericModel.setId(Long.valueOf((Integer)keys.get("id")));
		} catch (Exception e) {
			throw e;
		}
		return genericModel;
	}
	
	@Override
	public boolean update(GenericModel genericModel) {
		
		boolean update = false;
        try {
        	       
    		KeyHolder holder = new GeneratedKeyHolder();
    		
        	template.update("update livro set "
        						+ "titulo=:titulo"
        						+ "autor=:autor"
        						+ "tema=:tema"
        						+ "where id=:id", 
        			genericModel.getParams(), holder);
        	
        	update = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
        
       return update;
	}
	
	@Override
	public boolean deleteById(Long id) throws IllegalArgumentException {
		
		final String sql = "delete from " + getElementClass() + " where id=:id";
		 
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("id", id);
		 
		template.execute(sql,map,new PreparedStatementCallback<Object>() {  
		    @Override  
		    public Object doInPreparedStatement(PreparedStatement ps)  
		            throws SQLException, DataAccessException {
		        return ps.executeUpdate();  
		    }  
		});  
	
		return false;
	}
	
	protected abstract String getElementClass();
	protected abstract GenericModel getModel(ResultSet rs) throws SQLException;
}
