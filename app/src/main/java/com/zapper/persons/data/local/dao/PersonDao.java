package com.zapper.persons.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zapper.persons.data.local.entity.PersonEntity;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    LiveData<List<PersonEntity>> getPersons();

    @Query("SELECT * FROM person WHERE id = :id ")
    LiveData<PersonEntity> getPersonById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePersons(List<PersonEntity> personsEntities);

    @Delete
    void deletePersons(List<PersonEntity> personsEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePerson(PersonEntity personsEntities);

}
