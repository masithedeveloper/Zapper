package com.zapper.persons.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zapper.persons.data.local.entity.PersonEntity;
import com.zapper.persons.databinding.ItemPersonListBinding;
import com.zapper.persons.view.base.BaseAdapter;
import com.zapper.persons.view.callbacks.PersonListCallback;

import java.util.ArrayList;
import java.util.List;

public class PersonListAdapter extends  BaseAdapter<PersonListAdapter.PersonViewHolder, PersonEntity> {

    private List<PersonEntity> personEntities;

    private final PersonListCallback personListCallback;

    public PersonListAdapter(@NonNull PersonListCallback personListCallback) {
        personEntities = new ArrayList<>();
        this.personListCallback = personListCallback;
    }

    @Override
    public void setData(List<PersonEntity> entities) {
        this.personEntities = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return PersonViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, personListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder viewHolder, int i) {
        viewHolder.onBind(personEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return personEntities.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        private static PersonViewHolder create(LayoutInflater inflater, ViewGroup parent, PersonListCallback callback) {
            ItemPersonListBinding itemPersonListBinding = ItemPersonListBinding.inflate(inflater, parent, false);
            return new PersonViewHolder(itemPersonListBinding, callback);
        }

        final ItemPersonListBinding binding;

        private PersonViewHolder(ItemPersonListBinding binding, PersonListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onPersonClicked(binding.getPerson()));
        }

        private void onBind(PersonEntity personEntity) {
            binding.setPerson(personEntity);
            binding.executePendingBindings();
        }
    }
}
