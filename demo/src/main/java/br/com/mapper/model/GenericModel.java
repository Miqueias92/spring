package br.com.mapper.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GenericModel {
	
	protected Long id;

	GenericModel() {
		
	}
	
	GenericModel(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public abstract SqlParameterSource getParams();
	
	@JsonIgnore
	public abstract void setResultSet(ResultSet rs) throws SQLException;
}
