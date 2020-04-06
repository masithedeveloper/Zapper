package com.zapper.persons.view.callbacks;

import com.zapper.persons.data.local.entity.PersonEntity;

public interface PersonListCallback {
    void onPersonClicked(PersonEntity personEntity);
}

