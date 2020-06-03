package br.com.multidbprofile.config.postgres;

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
@Profile("postgres")
@EnableJpaRepositories(
		basePackages = "br.com.multidbprofile.repository",
		entityManagerFactoryRef = "appPostgresEntityManager")
public class ConfigPostgres {

	@Autowired
	private Environment env;
	
	@Bean
	@ConfigurationProperties(prefix = "postgres.datasource")
	public DataSource appPostgresDataSource() {
		
		String ambiente = env.getProperty("postgres.datasource.jdbcUrl");
		System.out.println(">>>>>>>>> ambiente: " + ambiente);
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean appPostgresEntityManager(
			EntityManagerFactoryBuilder builder,
			@Qualifier("appPostgresDataSource") DataSource dataSource){
		
		return builder
				.dataSource(dataSource)
				.packages("br.com.multidbprofile.model")
				.build();
	}
}
