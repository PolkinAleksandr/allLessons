package aleksandrpolkin.ru.lesson7;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aleksandrpolkin.ru.lesson7.data.ObjectsData;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecycle extends Fragment {

    static final String ARGUMENT_FRAGMENT_RECYCLE = "arg_frag_rec";
    static final String FRAGMENT_RECYCLE_TAG = "frag_rec_tag";
    private List<ObjectsData> objectsData;
    private RecyclerView.Adapter adapter;


    public static FragmentRecycle createInstance(List<ObjectsData> objectsData){
        FragmentRecycle fragment = new FragmentRecycle();
        Bundle arg = new Bundle();
        arg.putParcelableArrayList(ARGUMENT_FRAGMENT_RECYCLE, (ArrayList<? extends Parcelable>) objectsData);
        fragment.setArguments(arg);
        return fragment;
    }

    public FragmentRecycle() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            this.objectsData = getArguments().getParcelableArrayList(ARGUMENT_FRAGMENT_RECYCLE);
            getArguments().remove(ARGUMENT_FRAGMENT_RECYCLE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycle, container, false);
        final RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        adapter = new RecycleAdapter(objectsData);
        RecycleAdapter.MyViewHolder myViewHolder;
        recyclerView.setAdapter(adapter);
        return v;
    }


}
