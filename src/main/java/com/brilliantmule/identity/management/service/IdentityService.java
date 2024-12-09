package com.brilliantmule.identity.management.service;

import java.util.List;

public interface IdentityService {
    List<com.brilliantmule.identity.management.model.Identity> getIdentityList();

    com.brilliantmule.identity.management.model.Identity getIdentityById(Long id);

    com.brilliantmule.identity.management.model.Identity findIdentity(String email, String salesforceId);

    com.brilliantmule.identity.management.model.Identity saveIdentity(com.brilliantmule.identity.management.model.Identity identity);

    com.brilliantmule.identity.management.model.Identity updateIdentity(com.brilliantmule.identity.management.model.Identity identity, Long id);

    void deleteIdentityById(Long id);
}
