package com.zapper.persons.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.zapper.persons.data.local.dao.PersonDao;
import com.zapper.persons.data.local.entity.PersonEntity;

@Database(entities = {PersonEntity.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    public abstract PersonDao personDao();
}