package com.brilliantmule.identity.management.controller;

import com.brilliantmule.identity.management.exception.IdentityAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(name = "Identities")
public class IdentityController {

    @Autowired
    com.brilliantmule.identity.management.service.IdentityService identityService;

    @Autowired
    com.brilliantmule.identity.management.service.HealthService healthService;

    @GetMapping("/identities")
    @Operation(operationId = "get-identities", summary = "Get identities", description = "Get identity records")
    private ResponseEntity<List<com.brilliantmule.identity.management.model.Identity>> getIdentities(@Parameter(description = "Email address") @RequestParam(required = false) String email, @Parameter(description = "Salesforce id") @RequestParam(required = false) String salesforceId) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<com.brilliantmule.identity.management.model.Identity> identities = new ArrayList<>();
        if( email != null || salesforceId != null ){
            com.brilliantmule.identity.management.model.Identity identity = identityService.findIdentity(email, salesforceId);
            if( identity != null ){
                identities.add(identity);
            }
            return ResponseEntity.status(HttpStatus.OK).body(identities);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(identityService.getIdentityList());
        }
    }

    @GetMapping("/identities/{id}")
    @Operation(operationId = "get-identity-by-id", summary = "Get identity by ID", description = "Get the identity associated with the specified ID")
    private ResponseEntity<com.brilliantmule.identity.management.model.Identity> getIdentity(@Parameter(description = "Identity's unique identifier", schema = @Schema(type = "integer", format = "int64", example = "101")) @PathVariable("id") Long id) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        com.brilliantmule.identity.management.model.Identity identity;

        try {
            identity = identityService.getIdentityById(id);
        } catch (com.brilliantmule.identity.management.exception.IdentityNotFoundException infe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, infe.getMessage(), infe);
        } catch (RuntimeException re) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage(), re);
        }

        return ResponseEntity.status(HttpStatus.OK).body(identity);
    }

    @DeleteMapping("/identities/{id}")
    @Operation(operationId = "delete-identity-by-id", summary = "Delete identity by ID", description = "Delete the identity associated with the specified ID")
    private void deleteIdentity(@Parameter(description = "Identity's unique identifier", schema = @Schema(type = "integer", format = "int64", example = "101")) @PathVariable("id") Long id) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            identityService.deleteIdentityById(id);
        } catch (com.brilliantmule.identity.management.exception.IdentityNotFoundException infe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, infe.getMessage(), infe);
        } catch (RuntimeException re) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage(), re);
        }
    }

    @PostMapping("/identities")
    @Operation(operationId = "create-identity", summary = "Create an identity", description = "Create a new identity record using the information provided")
    private ResponseEntity<com.brilliantmule.identity.management.model.Identity> saveIdentity(@RequestBody com.brilliantmule.identity.management.model.Identity identity) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        com.brilliantmule.identity.management.model.Identity newIdentity = new com.brilliantmule.identity.management.model.Identity();

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
    @Operation(operationId = "update-identity-by-id", summary = "Update an identity by ID", description = "Update the identity associated with the specified ID")
    private ResponseEntity<com.brilliantmule.identity.management.model.Identity> updateIdentity(@RequestBody com.brilliantmule.identity.management.model.Identity identity, @Parameter(description = "Identity's unique identifier", schema = @Schema(type = "integer", format = "int64", example = "101")) @PathVariable("id") Long id) {
        if(!healthService.isServiceHealthy()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        com.brilliantmule.identity.management.model.Identity updatedIdentity;

        try {
            updatedIdentity = identityService.updateIdentity(identity, id);
        } catch (com.brilliantmule.identity.management.exception.IdentityNotFoundException infe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, infe.getMessage(), infe);
        } catch (RuntimeException re) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, re.getMessage(), re);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedIdentity);
    }
}
