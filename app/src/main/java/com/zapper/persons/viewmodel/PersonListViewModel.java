package com.zapper.persons.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.zapper.persons.data.local.entity.PersonEntity;
import com.zapper.persons.data.remote.Resource;
import com.zapper.persons.data.remote.repository.PersonRepository;

import java.util.List;

import javax.inject.Inject;

public class PersonListViewModel extends ViewModel {

    private final LiveData<Resource<List<PersonEntity>>> persons;

    @Inject
    public PersonListViewModel(PersonRepository personRepository) {
        persons = personRepository.loadPersons();
    }

    public LiveData<Resource<List<PersonEntity>>> getPersons() {
        return persons;
    }
}
