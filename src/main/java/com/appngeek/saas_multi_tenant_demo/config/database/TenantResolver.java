package com.appngeek.saas_multi_tenant_demo.config.database;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.appngeek.saas_multi_tenant_demo.domain.master.TenantDatabaseMapping;
import com.appngeek.saas_multi_tenant_demo.domain.master.UserTenantMapping;
import com.appngeek.saas_multi_tenant_demo.repo.master.TenantDatabaseMappingRepository;
import com.appngeek.saas_multi_tenant_demo.repo.master.UserTenantMappingRepository;

@Component
public class TenantResolver {

	@Autowired
	private TenantDatabaseMappingRepository tenantDatabaseMappingRepository;

	@Autowired
	private UserTenantMappingRepository userTenantMappingRepository;

	public String findDataBaseNameByTenantId(Long tenantId) {
		if (tenantId == null)
			return null;
		try {
			Optional<TenantDatabaseMapping> tenantDatabaseMapping = tenantDatabaseMappingRepository.findById(tenantId);
			if (tenantDatabaseMapping.isPresent())
				return tenantDatabaseMapping.get().getDatabaseName();
			else
				return null;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String findDataBaseNameByUsername(String username) {
		if (username == null)
			return null;

		try {
			UserTenantMapping userTenantMapping = userTenantMappingRepository.findByUserName(username);
			return userTenantMapping.getTenantDatabaseMapping().getDatabaseName();
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public Long findTenantIdByUsername(String username) {
		if (username == null)
			return null;
		try {
			UserTenantMapping userTenantMapping = userTenantMappingRepository.findByUserName(username);
			return userTenantMapping.getTenantDatabaseMapping().getId();

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}