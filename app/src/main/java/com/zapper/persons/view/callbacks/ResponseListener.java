package com.zapper.persons.view.callbacks;

import com.zapper.persons.data.local.entity.PersonEntity;

public interface ResponseListener {

    void onSuccess(PersonEntity data);
    void onFailure(String message);
}
