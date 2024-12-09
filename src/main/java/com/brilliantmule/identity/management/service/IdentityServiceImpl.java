package com.brilliantmule.identity.management.service;

import com.brilliantmule.identity.management.exception.IdentityAlreadyExistsException;
import com.brilliantmule.identity.management.exception.IdentityNotFoundException;
import com.brilliantmule.identity.management.model.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IdentityServiceImpl implements IdentityService {
    @Autowired
    com.brilliantmule.identity.management.repository.IdentityRepository IdentityRepository;

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

    public Identity findIdentity(String email, String salesforceId) {
        Optional<Identity> existingIdentity;

        if(email == null && salesforceId == null){
            return null;
        } else if(salesforceId == null){
            existingIdentity = IdentityRepository.findByEmail(email);
        } else if(email == null){
            existingIdentity = IdentityRepository.findBySalesforceId(salesforceId);
        } else {
            existingIdentity = IdentityRepository.findByEmailOrSalesforceId(email, salesforceId);
        }

        if(existingIdentity.isEmpty()){
            return null;
        } else {
            return existingIdentity.get();
        }
    }

    public Identity saveIdentity(Identity identity) {
        // Check if identity already exists in DB
        if (isNotNull(identity.getEmail())) {
            if (IdentityRepository.findByEmail(identity.getEmail()).isPresent()) {
                throw new IdentityAlreadyExistsException("Identity already exists");
            }
        } else if (isNotNull(identity.getSalesforceId())) {
            if (IdentityRepository.findBySalesforceId(identity.getSalesforceId()).isPresent()) {
                throw new IdentityAlreadyExistsException("Identity already exists");
            }
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

        if (isNotNull(identityUpdates.getSalesforceId())) {
            existingIdentity.setSalesforceId(identityUpdates.getSalesforceId());
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
