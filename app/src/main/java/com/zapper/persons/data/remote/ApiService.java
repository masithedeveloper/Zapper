package com.zapper.persons.data.remote;

import com.zapper.persons.data.remote.model.PersonResponse;
import com.zapper.persons.data.remote.model.PersonsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("persons")
    Call<PersonsResponse> getPersons();

    @GET("persons/{id}")
    Call<PersonResponse> getPersonById(@Path("id") long id);
}
