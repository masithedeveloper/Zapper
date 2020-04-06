package com.zapper.persons.di.builder;

import com.zapper.persons.view.fragment.PersonDetailFragment;
import com.zapper.persons.view.fragment.PersonListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract PersonListFragment contributePersonListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract PersonDetailFragment contributePersonDetailFragment();

}
