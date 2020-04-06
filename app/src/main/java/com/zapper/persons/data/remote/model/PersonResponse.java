package com.zapper.persons.data.remote.model;

import com.zapper.persons.data.local.entity.PersonEntity;


public class PersonResponse {

    private PersonEntity personResponse;

    public PersonEntity getPerson() {
        return personResponse;
    }

    public void setPerson(PersonEntity personResponse) {
        this.personResponse = personResponse;
    }
}
