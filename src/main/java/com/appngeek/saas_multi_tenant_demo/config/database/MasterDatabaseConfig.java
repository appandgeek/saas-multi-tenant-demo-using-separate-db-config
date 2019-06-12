package com.appngeek.saas_multi_tenant_demo.config.database;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.appngeek.saas_multi_tenant_demo.repo.master", entityManagerFactoryRef = "masterEntityManager",
		transactionManagerRef = "masterTransactionManager")
@EnableTransactionManagement
public class MasterDatabaseConfig {

	@Autowired
	private Environment env;

	@Bean("masterConfig")
	@ConfigurationProperties(prefix = "spring.master.datasource")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean(name = "masterDataSource")
	public DataSource masterDataSource() {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean masterEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(masterDataSource());
		em.setPackagesToScan(new String[]{"com.appngeek.saas_multi_tenant_demo.domain.master"});
		em.setPersistenceUnitName("master-db-persistence-unit");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public PlatformTransactionManager masterTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(masterEntityManager().getObject());
		return transactionManager;
	}

}