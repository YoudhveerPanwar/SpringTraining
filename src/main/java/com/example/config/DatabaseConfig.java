package com.example.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.entities.User;

@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfig {
	
	@Autowired
	private Environment env;
	
	private static final Logger logger = Logger.getLogger(DatabaseConfig.class.getSimpleName());   
	
	@Bean
	public DataSource datasource() {
		logger.log(Level.INFO, "Database config starts..");
		return dbDataSource();
	}
	
	public DataSource dbDataSource() {
		logger.log(Level.INFO, "CREATING DATASOURCE INSTANCE for url {0} ", env.getProperty("spring.datasource.url"));
		return DataSourceBuilder.create()
				.driverClassName(env.getProperty("spring.datasource.driver-class-name"))
				.url(env.getProperty("spring.datasource.url"))
				.username(env.getProperty("spring.datasource.username"))
				.password(env.getProperty("spring.datasource.password"))
				.build();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(User.class.getPackage().getName());
		factory.setDataSource(datasource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}
}
