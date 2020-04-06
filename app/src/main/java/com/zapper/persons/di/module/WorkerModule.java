package com.zapper.persons.di.module;


import androidx.work.Worker;

import com.zapper.persons.data.remote.FetchPersonsWorker;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WorkerModule {

    @Binds
    @SuppressWarnings("unused")
    abstract Worker bindsFetchPersonsWorker(FetchPersonsWorker fetchPersonsWorker);
}
