package com.brilliantmule.identity.management.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IdentityRepository extends CrudRepository<com.brilliantmule.identity.management.model.Identity, Long> {
    Optional<com.brilliantmule.identity.management.model.Identity> findByEmail(String email);
    Optional<com.brilliantmule.identity.management.model.Identity> findBySalesforceId(String salesforceId);
    Optional<com.brilliantmule.identity.management.model.Identity> findByEmailOrSalesforceId(String email,String salesforceId);
}
