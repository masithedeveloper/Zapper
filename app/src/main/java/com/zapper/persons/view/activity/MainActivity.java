package com.zapper.persons.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.zapper.persons.R;
import com.zapper.persons.data.remote.FetchPersonsWorker;
import com.zapper.persons.databinding.ActivityMainBinding;
import com.zapper.persons.utils.FragmentUtils;
import com.zapper.persons.view.base.BaseActivity;
import com.zapper.persons.view.fragment.PersonListFragment;

import java.util.concurrent.TimeUnit;

import static com.zapper.persons.utils.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final String TAG = "PersonsUpdate";

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, PersonListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);

        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(FetchPersonsWorker.class, 15, TimeUnit.MINUTES)
                .addTag(TAG)
                .build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("Persons", ExistingPeriodicWorkPolicy.REPLACE, periodicWork);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
