package com.zapper.persons.data.remote.model;

import com.google.gson.annotations.SerializedName;
import com.zapper.persons.data.local.entity.PersonEntity;

import java.util.List;

public class PersonsResponse {

    @SerializedName("persons")
    private List<PersonEntity> personsResponse;

    public List<PersonEntity> getPersons() {
        return personsResponse;
    }

    public void setPersons(List<PersonEntity> personsResponse) {
        this.personsResponse = personsResponse;
    }
}
