package com.zapper.persons.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.zapper.persons.data.remote.repository.PersonRepository;


import javax.inject.Inject;

public class FetchPersonsWorker extends Worker {

    private PersonRepository personRepository;

    @Inject
    public FetchPersonsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (null != personRepository)
            return personRepository.workerLoadPersons();
        return null;
    }
}

