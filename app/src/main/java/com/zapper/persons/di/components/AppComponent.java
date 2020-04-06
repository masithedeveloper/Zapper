package com.zapper.persons.di.components;

import android.app.Application;

import com.zapper.persons.ZapperApp;
import com.zapper.persons.di.builder.ActivityBuilderModule;
import com.zapper.persons.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(ZapperApp zapperApp);
}