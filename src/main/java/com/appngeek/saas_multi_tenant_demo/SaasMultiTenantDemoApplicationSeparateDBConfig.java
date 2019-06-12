package com.appngeek.saas_multi_tenant_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SaasMultiTenantDemoApplicationSeparateDBConfig {

	public static void main(String[] args) {
		SpringApplication.run(SaasMultiTenantDemoApplicationSeparateDBConfig.class, args);
	}

}
