package com.appngeek.saas_multi_tenant_demo.repo.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appngeek.saas_multi_tenant_demo.domain.master.UserTenantMapping;

@Repository
public interface UserTenantMappingRepository extends JpaRepository<UserTenantMapping, String> {

	UserTenantMapping findByUserName(String userName);

}