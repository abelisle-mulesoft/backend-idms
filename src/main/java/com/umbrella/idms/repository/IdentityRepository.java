package com.umbrella.idms.repository;

import com.umbrella.idms.model.Identity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IdentityRepository extends CrudRepository<Identity, Long> {
    Optional<Identity> findByEmail(String email);
    Optional<Identity> findBySfContactId(String sfContactId);
}
