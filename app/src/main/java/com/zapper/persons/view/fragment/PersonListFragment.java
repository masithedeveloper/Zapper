package com.zapper.persons.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zapper.persons.R;
import com.zapper.persons.common.Constants;
import com.zapper.persons.data.local.entity.PersonEntity;
import com.zapper.persons.data.remote.Status;
import com.zapper.persons.databinding.FragmentListPersonsBinding;
import com.zapper.persons.utils.FragmentUtils;
import com.zapper.persons.view.adapter.PersonListAdapter;

import com.zapper.persons.view.base.BaseFragment;
import com.zapper.persons.view.callbacks.PersonListCallback;
import com.zapper.persons.viewmodel.PersonListViewModel;

public class PersonListFragment extends BaseFragment<PersonListViewModel, FragmentListPersonsBinding> implements PersonListCallback {

    public static PersonListFragment newInstance() {
        Bundle args = new Bundle();
        PersonListFragment fragment = new PersonListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<PersonListViewModel> getViewModel() {
        return PersonListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list_persons;
    }

    @Override
    public void onPersonClicked(PersonEntity personEntity) {
        if(null != getActivity()){
            Bundle args = new Bundle();
            args.putLong(Constants.BUNDLE_KEY_PERSON_ID, personEntity.getId());
            PersonDetailFragment detailFragment = new PersonDetailFragment();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.fragContainer, true, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new PersonListAdapter(this));
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getPersons()
                .observe(this, listResource -> {
                    if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                        dataBinding.progressBar.setVisibility(View.GONE);
                    }
                    dataBinding.setResource(listResource);

                    // If the cached data is already showing then no need to show the error
                    if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });

    }
}
