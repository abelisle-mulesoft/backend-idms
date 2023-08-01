package com.umbrella.idms.service;

import com.umbrella.idms.model.Identity;

import java.util.List;

public interface IdentityService {
    List<Identity> getIdentityList();

    Identity getIdentityById(Long id);

    Identity findIdentity(String email, String sfContactId);

    Identity saveIdentity(Identity identity);

    Identity updateIdentity(Identity identity, Long id);

    void deleteIdentityById(Long id);
}
