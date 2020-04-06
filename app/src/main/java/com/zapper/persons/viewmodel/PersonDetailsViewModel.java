package com.zapper.persons.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.zapper.persons.data.local.entity.PersonEntity;
import com.zapper.persons.data.remote.repository.PersonRepository;
import com.zapper.persons.utils.SingleLiveEvent;
import com.zapper.persons.view.callbacks.ResponseListener;

import javax.inject.Inject;

public class PersonDetailsViewModel extends ViewModel {

    private long id;

    private PersonRepository personRepository;

    private MutableLiveData<PersonEntity> personEntityMutableLiveData = new MutableLiveData<>();

    private SingleLiveEvent<Void> errorMessageRecieved = new SingleLiveEvent<>();

    public MutableLiveData<PersonEntity> getPersonEntityMutableLiveData() {
        return personEntityMutableLiveData;
    }

    public void setPersonEntityMutableLiveData(MutableLiveData<PersonEntity> personEntityMutableLiveData) {
        this.personEntityMutableLiveData = personEntityMutableLiveData;
    }

    public SingleLiveEvent<Void> getErrorMessageRecieved() {
        return errorMessageRecieved;
    }

    public void setErrorMessageRecieved(SingleLiveEvent<Void> errorMessageRecieved) {
        this.errorMessageRecieved = errorMessageRecieved;
    }

    @Inject
    PersonDetailsViewModel(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public long geId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void loadPersonDetails(){

        if(null != personRepository) {
            personRepository.loadPersonDetails(id, new ResponseListener() {
                @Override
                public void onSuccess(PersonEntity data) {
                    personEntityMutableLiveData.setValue(data);
                }

                @Override
                public void onFailure(String message) {
                    // Send event to UI to show thw error
                    errorMessageRecieved.call();
                }
            });
        }
    }
}
