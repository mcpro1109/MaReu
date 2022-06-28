package com.example.mareu.lists;

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
import com.example.mareu.model.RoomApiService;

public class RoomFragmentDialog extends DialogFragment {

    RecyclerView recyclerView;
    private final RoomApiService roomService = DI.getRoomService();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rooms_filtre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.filtreRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SalleFragmentRecyclerViewAdapter(roomService.getRooms(), (view1, room) -> {
            // ReunionsListActivity.filterByRoom(room);
            ReunionsListActivity.filterByRoom(room);
            //filtre par salle dans la recyclerview
            dismiss();
        }));
    }
}
