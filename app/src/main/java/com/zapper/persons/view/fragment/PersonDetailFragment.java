package com.zapper.persons.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zapper.persons.R;
import com.zapper.persons.common.Constants;

import com.zapper.persons.databinding.FragmentPersonDetailsBinding;
import com.zapper.persons.view.base.BaseFragment;
import com.zapper.persons.viewmodel.PersonDetailsViewModel;

public class PersonDetailFragment extends BaseFragment<PersonDetailsViewModel, FragmentPersonDetailsBinding> {
    @Override
    protected Class<PersonDetailsViewModel> getViewModel() {
        return PersonDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_person_details;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if(null != args) {
            viewModel.setId(args.getLong(Constants.BUNDLE_KEY_PERSON_ID));
            viewModel.loadPersonDetails();
        }
        viewModel.getPersonEntityMutableLiveData().observe(this, personEntity -> {
            if(null != personEntity && null != args) {
                dataBinding.textTitle.setText(personEntity.getName());
                dataBinding.loadingProgress.setVisibility(View.GONE);
            }
        });

        viewModel.getErrorMessageRecieved().observe(this, message ->{
            dataBinding.loadingProgress.setVisibility(View.GONE);
            dataBinding.textContent.setText(getActivity().getString(R.string.networkError));
        });
    }
}
