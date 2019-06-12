package com.appngeek.saas_multi_tenant_demo.config.database;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.appngeek.saas_multi_tenant_demo.repo.tenant", entityManagerFactoryRef = "tenantEntityManager",
		transactionManagerRef = "tenantTransactionManager")
@EnableTransactionManagement
public class TenantDatabaseConfig {

	@Autowired
	private Environment env;

	@Bean(name = "tenantAwareDataSource")
	@Primary
	public DataSource tenantAwareDataSource() {
		return new TenantAwareDataSource();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean tenantEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(tenantAwareDataSource());
		em.setPackagesToScan(new String[]{"com.appngeek.saas_multi_tenant_demo.domain.tenant"});
		em.setPersistenceUnitName("tenant-db-persistence-unit");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	@Primary
	public PlatformTransactionManager tenantTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(tenantEntityManager().getObject());
		return transactionManager;
	}
}