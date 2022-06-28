package com.example.mareu.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.methods.Room;

import java.util.List;

public class SalleFragmentRecyclerViewAdapter extends RecyclerView.Adapter<SalleFragmentRecyclerViewAdapter.ViewHolder> {

    private final List<Room> salleReunions;
    private RecyclerViewClickListener listener;

    public SalleFragmentRecyclerViewAdapter(List<Room> salleReunions, RecyclerViewClickListener listener) {
        this.salleReunions = salleReunions;
        this.listener = listener;
    }

    @Override
    public SalleFragmentRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_filtre_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalleFragmentRecyclerViewAdapter.ViewHolder holder, int position) {
        //ajouter toutes les salles de réunion
        Room meetingRoom = salleReunions.get(position);
        holder.mRoom.setText(meetingRoom.getName());

        holder.itemView.setOnClickListener(v -> listener.onClick(v, meetingRoom));
    }

    @Override
    public int getItemCount() {
        return salleReunions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mRoom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRoom = itemView.findViewById(R.id.RoomName);
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, Room room);
    }

}
