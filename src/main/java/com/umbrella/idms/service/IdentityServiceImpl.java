package com.umbrella.idms.service;

import com.umbrella.idms.exception.IdentityAlreadyExistsException;
import com.umbrella.idms.exception.IdentityNotFoundException;
import com.umbrella.idms.model.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IdentityServiceImpl implements IdentityService {
    @Autowired
    com.umbrella.idms.repository.IdentityRepository IdentityRepository;

    public List<Identity> getIdentityList() {
        return (List<Identity>) IdentityRepository.findAll();
    }

    public Identity getIdentityById(Long id) {
        Optional<Identity> identity = IdentityRepository.findById(id);

        if(identity.isEmpty()){
            throw new IdentityNotFoundException("Identity not found");
        }

        return identity.get();
    }

    public Identity findIdentity(String email, String sfContactId) {
        Optional<Identity> existingIdentity = IdentityRepository.findByEmail(email);

        if(existingIdentity.isEmpty()){
            existingIdentity = IdentityRepository.findBySfContactId(sfContactId);
        }

        if(existingIdentity.isEmpty()){
            return new Identity();
        } else {
            return existingIdentity.get();
        }
    }

    public Identity saveIdentity(Identity identity) {
        // Check if Identity already exists in DB
        if (IdentityRepository.findByEmail(identity.getEmail()).isPresent() ||
                IdentityRepository.findBySfContactId(identity.getSFContactId()).isPresent()) {
            throw new IdentityAlreadyExistsException("Identity already exists");
        }

        return IdentityRepository.save(identity);
    }

    public Identity updateIdentity(Identity identityUpdates, Long id) {
        if (!IdentityRepository.existsById(id)) {
            throw new IdentityNotFoundException("Identity not found");
        }

        Identity existingIdentity = IdentityRepository.findById(id).get();

        if (isNotNull(identityUpdates.getFirstName())) {
            existingIdentity.setFirstName(identityUpdates.getFirstName());
        }

        if (isNotNull(identityUpdates.getLastName())) {
            existingIdentity.setLastName(identityUpdates.getLastName());
        }

        if (isNotNull(identityUpdates.getEmail())) {
            existingIdentity.setEmail(identityUpdates.getEmail());
        }

        if (isNotNull(identityUpdates.getSFContactId())) {
            existingIdentity.setSFContactId(identityUpdates.getSFContactId());
        }

        if (isNotNull(identityUpdates.getStreet())) {
            existingIdentity.setStreet(identityUpdates.getStreet());
        }

        if (isNotNull(identityUpdates.getCity())) {
            existingIdentity.setCity(identityUpdates.getCity());
        }

        if (isNotNull(identityUpdates.getState())) {
            existingIdentity.setState(identityUpdates.getState());
        }

        if (isNotNull(identityUpdates.getZip())) {
            existingIdentity.setZip(identityUpdates.getZip());
        }

        return IdentityRepository.save(existingIdentity);
    }

    public void deleteIdentityById(Long id) {
        // Check if entity exists and delete it
        if (IdentityRepository.existsById(id)) {
            IdentityRepository.deleteById(id);
        } else {
            throw new IdentityNotFoundException("Identity not found");
        }
    }

    private boolean isNotNull(String value) {
        return Objects.nonNull(value) && !value.equals("");
    }

}
