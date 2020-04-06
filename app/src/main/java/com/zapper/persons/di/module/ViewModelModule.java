package com.zapper.persons.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.zapper.persons.viewmodel.PersonDetailsViewModel;
import com.zapper.persons.viewmodel.PersonListViewModel;
import com.zapper.persons.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PersonListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsPersonListViewModel(PersonListViewModel personListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PersonDetailsViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsPersonDetailsiewModel(PersonDetailsViewModel personDetailsViewModel);

    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
