package com.zapper.persons.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import androidx.work.ListenableWorker;

import com.zapper.persons.data.local.dao.PersonDao;
import com.zapper.persons.data.local.entity.PersonEntity;
import com.zapper.persons.data.remote.ApiService;
import com.zapper.persons.data.remote.NetworkBoundResource;
import com.zapper.persons.data.remote.Resource;
import com.zapper.persons.data.remote.model.PersonResponse;
import com.zapper.persons.data.remote.model.PersonsResponse;
import com.zapper.persons.view.callbacks.ResponseListener;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class PersonRepository {

    private final PersonDao personDao;
    private final ApiService apiService;

    @Inject
    PersonRepository(PersonDao dao, ApiService service) {
        this.personDao = dao;
        this.apiService = service;
    }

    public LiveData<Resource<List<PersonEntity>>> loadPersons() {
        return new NetworkBoundResource<List<PersonEntity>, PersonsResponse>() {

            @Override
            protected void saveCallResult(PersonsResponse item) {
                if(null != item)
                    personDao.savePersons(item.getPersons());
            }

            @NonNull
            @Override
            protected LiveData<List<PersonEntity>> loadFromDb() {
                return personDao.getPersons();
            }

            @NonNull
            @Override
            protected Call<PersonsResponse> createCall() {
                return apiService.getPersons();
            }
        }.getAsLiveData();
    }

    public ListenableWorker.Result workerLoadPersons() {
        new NetworkBoundResource<List<PersonEntity>, PersonsResponse>() {
            @Override
            protected void saveCallResult(PersonsResponse item) {
                if(null != item)
                    personDao.savePersons(item.getPersons());
            }

            @NonNull
            @Override
            protected LiveData<List<PersonEntity>> loadFromDb() {
                return personDao.getPersons();
            }

            @NonNull
            @Override
            protected Call<PersonsResponse> createCall() {
                personDao.deletePersons((personDao.getPersons().getValue()));
                return apiService.getPersons();
            }
        }.getAsLiveData();

        return null;
    }

    public LiveData<Resource<PersonEntity>> loadPersonDetails(long id, ResponseListener responseListener) {
        return new NetworkBoundResource<PersonEntity, PersonResponse>() {

            @Override
            protected void saveCallResult(PersonResponse item) {
                if (null != item) {
                    personDao.savePerson(item.getPerson());
                    responseListener.onSuccess(item.getPerson());
                }
            }

            @NonNull
            @Override
            protected LiveData<PersonEntity> loadFromDb() {
                return personDao.getPersonById(id);
            }

            @NonNull
            @Override
            protected Call<PersonResponse> createCall() {
                Call<PersonResponse> personResponse = apiService.getPersonById(id);
                if (personResponse != null)
                    responseListener.onSuccess((PersonEntity) personResponse);
                else
                    responseListener.onFailure("Failed to fetch person details");
                return personResponse;
            }
        }.getAsLiveData();
    }
}

