package com.umbrella.idms.controller;

import com.umbrella.idms.exception.IdentityAlreadyExistsException;
import com.umbrella.idms.exception.IdentityNotFoundException;
import com.umbrella.idms.model.Identity;
import com.umbrella.idms.service.HealthService;
import com.umbrella.idms.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/idms")
public class IdentityController {

    @Autowired
    IdentityService identityService;

    @Autowired
    HealthService healthService;

    @GetMapping("/identities")
    private ResponseEntity<List<Identity>> getAllIdentitys() {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.OK).body(identityService.getIdentityList());
    }

    @GetMapping("/identities/{id}")
    private ResponseEntity<Identity> getIdentity(@PathVariable("id") Long id) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Identity identity;

        try {
            identity = identityService.getIdentityById(id);
        } catch (IdentityNotFoundException infe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, infe.getMessage(), infe);
        } catch (RuntimeException re) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage(), re);
        }

        return ResponseEntity.status(HttpStatus.OK).body(identity);
    }

    @GetMapping("/identity")
    private Identity findIdentity(@RequestParam String email, @RequestParam String sfContactId) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return identityService.findIdentity(email, sfContactId);
    }

// Return empty response body
//    @RequestMapping(value = "/sigla/{sigla}", method = RequestMethod.GET, consumes = "application/json", produces="application/json")
//    public ResponseEntity<PaisDTO> obterPorSigla(@PathVariable String sigla) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//        PaisDTO paisDTO = service.obterPorSigla(sigla);
//        if(paisDTO != null) return new ResponseEntity<>(paisDTO, headers, HttpStatus.OK);
//        else return new ResponseEntity<>(headers, HttpStatus.OK);
//    }

    @DeleteMapping("/identities/{id}")
    private void deleteIdentity(@PathVariable("id") Long id) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            identityService.deleteIdentityById(id);
        } catch (IdentityNotFoundException infe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, infe.getMessage(), infe);
        } catch (RuntimeException re) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage(), re);
        }
    }

    @PostMapping("/identities")
    private ResponseEntity<Identity> saveIdentity(@RequestBody Identity identity) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Identity newIdentity = new Identity();

        try {
            newIdentity = identityService.saveIdentity(identity);
        } catch (IdentityAlreadyExistsException iaee) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iaee.getMessage(), iaee);
        } catch (RuntimeException re) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage(), re);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newIdentity);
    }

    @PutMapping("/identities/{id}")
    private ResponseEntity<Identity> updateIdentity(@RequestBody Identity identity, @PathVariable("id") Long id) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Identity updatedIdentity;

        try {
            updatedIdentity = identityService.updateIdentity(identity, id);
        } catch (IdentityNotFoundException infe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, infe.getMessage(), infe);
        } catch (RuntimeException re) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage(), re);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedIdentity);
    }
}
