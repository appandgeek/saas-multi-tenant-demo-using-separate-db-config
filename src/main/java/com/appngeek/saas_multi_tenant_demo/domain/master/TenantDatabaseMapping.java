package com.appngeek.saas_multi_tenant_demo.domain.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The data that is stored in tenant database and not in master db
 * 
 * @author tanmally
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "tenant")
public class TenantDatabaseMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "database_name", unique = true)
	private String databaseName;

	@Column(name = "tenant_name" , unique = true)
	private String tenantName;

	@Column(name = "enabled")
	private Boolean enabled;

}
