package br.com.mapper.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataBaseConfiguration {

	@Bean(name = "db1")
	@Primary
    @ConfigurationProperties(prefix="postgres.datasource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplate1")
    @Primary
    public JdbcTemplate postgresJdbcTemplate(@Qualifier("db1") DataSource postgresDataSource){
        return new JdbcTemplate(postgresDataSource);
    }
    
    @Bean(name = "db2")
    @ConfigurationProperties(prefix="mysql.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("db2") DataSource mysqlDataSource){
        return new JdbcTemplate(mysqlDataSource);
    }
}
