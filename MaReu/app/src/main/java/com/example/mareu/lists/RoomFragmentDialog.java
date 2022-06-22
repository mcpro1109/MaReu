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

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.methods.Reunion;
import com.example.mareu.methods.Room;
import com.example.mareu.model.ReunionApiService;
import com.example.mareu.model.RoomApiService;

import java.util.List;

public class RoomFragmentDialog extends DialogFragment {

    RecyclerView mRecyclerView;
    private RoomApiService mRoomApiService = DI.getRoomService();
    private ReunionApiService mReunionApiService = DI.getReunionService();
    private static List<Reunion> mReunion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rooms_filtre, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.filtreRoom);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

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
        mReunion= mReunionApiService.getReunions();

        mRecyclerView.setAdapter(new SalleFragmentRecyclerViewAdapter(mRoomApiService.getRooms(), new SalleFragmentRecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, Room room) {
               // ReunionsListActivity.filterByRoom(room);
                ReunionsListActivity.filterByRoom(room);
                //filtre par salle dans la recyclerview
                initList();
            }
        }));
    }
}
