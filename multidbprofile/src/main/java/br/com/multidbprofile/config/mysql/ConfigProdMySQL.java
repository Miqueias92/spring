package br.com.multidbprofile.config.mysql;

//@Configuration
//@EnableJpaRepositories(
//		basePackages = "br.com.multidbprofile.repository.mysql",
//		entityManagerFactoryRef = "appProdEntityManager")
public class ConfigProdMySQL {
	
//	@Bean
//	@ConfigurationProperties(prefix = "mysql.datasource")
//	public DataSource appMySQLProdDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean appProdEntityManager(
//			EntityManagerFactoryBuilder builder,
//			@Qualifier("appMySQLProdDataSource") DataSource dataSource){
//		
//		return builder
//				.dataSource(dataSource)
//				.packages("br.com.multidbprofile.model")
//				.build();
//	}
}
