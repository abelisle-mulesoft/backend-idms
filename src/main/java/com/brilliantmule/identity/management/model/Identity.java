package com.brilliantmule.identity.management.model;

import javax.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Identity {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(String salesforceId) {
        this.salesforceId = salesforceId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "id_sequence", initialValue = 1101)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @Column(name = "id", nullable = false)
    @Schema(
            description = "Identity's unique identifier",
            example = "101"
    )
    private Long id;

    @Column(name = "first_name")
    @Schema(
            description = "First name of the user",
            example = "Arthur"
    )
    private String firstName;

    @Column(name = "last_name")
    @Schema(
            description = "Last name of the user",
            example = "Song"
    )
    private String lastName;

    @Schema(
            description = "Email address of the user",
            example = "asong@brilliantmule.com"
    )
    @Column(name = "email", unique=true)
    private String email;

    @Schema(
            description = "Salesforce's unique identifier",
            example = "003Do00000HU7qtIAD"
    )
    @Column(name = "salesforce_id", unique = true)
    private String salesforceId;

    @Column(name = "address_street")
    @Schema(
            description = "Street address of the user",
            example = "1301 Avenue of the Americas"
    )
    private String street;

    @Column(name = "address_city")
    @Schema(
            description = "City of the user",
            example = "New York"
    )
    private String city;

    @Column(name = "address_state")
    @Schema(
            description = "State or province of the user",
            example = "NY"
    )
    private String state;

    @Column(name = "address_zip")
    @Schema(
            description = "Zip or postal code of the user",
            example = "10019"
    )
    private String zip;
}
