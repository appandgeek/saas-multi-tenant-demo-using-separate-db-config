package com.appngeek.saas_multi_tenant_demo.repo.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appngeek.saas_multi_tenant_demo.domain.master.TenantDatabaseMapping;

@Repository
public interface TenantDatabaseMappingRepository extends JpaRepository<TenantDatabaseMapping, Long> {

	List<TenantDatabaseMapping> findByTenantName(String tenantName);

}