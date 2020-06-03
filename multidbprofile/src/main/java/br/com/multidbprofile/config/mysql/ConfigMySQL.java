package br.com.multidbprofile.config.mysql;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@Profile("mysql")
@EnableJpaRepositories(
		basePackages = "br.com.multidbprofile.repository.mysql",
		entityManagerFactoryRef = "appEntityManager")
public class ConfigMySQL {
	
	@Autowired
	private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix = "mysql.datasource")
	public DataSource appMySQLDataSource() {
		
		String ambiente = env.getProperty("mysql.datasource.jdbcUrl");
		System.out.println(">>>>>>>>> ambiente: " + ambiente);
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean appEntityManager(
			EntityManagerFactoryBuilder builder,
			@Qualifier("appMySQLDataSource") DataSource dataSource){
		
		return builder
				.dataSource(dataSource)
				.packages("br.com.multidbprofile.model")
				.build();
	}
	
	// TODO: Rever possibilidade de usar os métodos abaixo, ainda que os mesmo se façam desnecessários
//	@Bean
//	@Profile("dev")
//	public ConfigDevMySQL ConfigDevMySQL() {
//		System.out.println(" \n\n ****** MySQL DEV ***** \n\n");
//		return new ConfigDevMySQL();
//	}
//	
//	@Bean
//	@Profile("prod")
//	public ConfigProdMySQL ConfigProdMySQL() {
//		System.out.println(" \n\n ****** MySQL PROD ***** \n\n");
//		return new ConfigProdMySQL();
//	}
}
