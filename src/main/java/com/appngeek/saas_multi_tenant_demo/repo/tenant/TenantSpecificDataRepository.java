package com.appngeek.saas_multi_tenant_demo.repo.tenant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appngeek.saas_multi_tenant_demo.domain.tenant.TenantSpecificData;

@Repository
public interface TenantSpecificDataRepository extends JpaRepository<TenantSpecificData, Long> {

	List<TenantSpecificData> findByUsername(String username);

	List<TenantSpecificData> findByCurrentTenantId(Long currentTenantId);

}