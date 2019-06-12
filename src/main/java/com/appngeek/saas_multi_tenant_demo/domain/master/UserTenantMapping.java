package com.appngeek.saas_multi_tenant_demo.domain.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "user_tenant")
public class UserTenantMapping {

	@Id
	@Column(name = "user_name")
	private String userName;

	@OneToOne(fetch = FetchType.EAGER)
	private TenantDatabaseMapping tenantDatabaseMapping;

	@Column(name = "enabled")
	private Boolean enabled;

}
