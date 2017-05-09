package mypoc.euh.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "mypoc.euh.repository")
@PropertySource(value = {"classpath:application.properties"})
public class DBConfig {

	private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);

	@Value("${spring.launch.db_manager}")
	private boolean LAUNCH_DB_MANAGER;

	@Value("${spring.datasource.url}")
	private String DATASOURCE_URL;

	@Value("${spring.datasource.username}")
	private String USER_NAME;
	
	@Value("${spring.jpa.database}")
	private String JPA_DATABASE;
	
	@Value("${spring.jpa.dialect}")
	private String DIALECT;
	
	@Value("${spring.jpa.driver}")
	private String DRIVER;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String HBM_DDL_AUTO;
	
	private String PASSWORD;
	
	private final String MODEL_PACKAGE = "mypoc.euh.entity";
	
	
	@Bean
	public DataSource dataSource() {
		logger.info("Started datasource");
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(DATASOURCE_URL);
		dataSource.setUsername(USER_NAME);
		
		if(LAUNCH_DB_MANAGER) {
			DatabaseManagerSwing.main(new String[] { "--url", DATASOURCE_URL, "--user", USER_NAME, "--password", PASSWORD });
		}
		
		logger.info("Ended datasource");
		return dataSource;
	}
	
	
	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		logger.info("Started hibernatejpavendor");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform(JPA_DATABASE);
		vendorAdapter.setShowSql(false);
		logger.info("Ended hibernatejpavendor");
		return vendorAdapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		logger.info("Started entityManagerFactory");
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		factoryBean.setPackagesToScan(MODEL_PACKAGE);
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", HBM_DDL_AUTO);
		jpaProperties.put("hibernate.dialect", DIALECT);
		jpaProperties.put("hibernate.connection.autocommit", false);
		jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		jpaProperties.put("hibernate.generate_statistics", true);
		jpaProperties.put("hibernate.show_sql", true);
		jpaProperties.put("hibernate.enable_lazy_load_no_trans", true);
		factoryBean.setJpaProperties(jpaProperties);
		factoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		logger.info("Ended entityManagerFactory");
		return factoryBean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		logger.info("Started transactionManager");
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		logger.info("Ended transactionManager");
		return transactionManager;
	}
}
