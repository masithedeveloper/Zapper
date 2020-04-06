package com.zapper.persons.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.zapper.persons.data.remote.Resource;
import com.zapper.persons.view.base.BaseAdapter;

import java.util.List;

final public class ListBindingAdapter{

    private ListBindingAdapter(){
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }

}
