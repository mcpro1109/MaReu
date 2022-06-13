package com.example.mareu.lists;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.DI.DI_rooms;
import com.example.mareu.R;
import com.example.mareu.methods.Room;
import com.example.mareu.model.SalleApiService;

import java.util.List;

public class SalleFragmentDialog extends DialogFragment {

    RecyclerView mRecyclerView;
    SalleFragmentRecyclerViewAdapter adapter;
    private SalleApiService mSalleApiService= DI_rooms.getService();
    private List<Room> mSalles;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_salles_filtre, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.filtreSalle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        mRecyclerView.setAdapter(adapter);
        initList();
        return view;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    //initialiser la liste des réunions
    private void initList() {
        mSalles = mSalleApiService.getSalles();
        mRecyclerView.setAdapter(new SalleFragmentRecyclerViewAdapter(mSalles, (view, reunion) -> {
            initList();
        }));
    }
}
