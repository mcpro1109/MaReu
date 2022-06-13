package com.example.mareu.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.methods.Room;
import com.example.mareu.model.ReunionApiService;

import java.util.List;

public class SalleFragmentRecyclerViewAdapter extends RecyclerView.Adapter<SalleFragmentRecyclerViewAdapter.ViewHolder> {
    private final List<Room> mSalleReunions;
    private RecyclerViewClickListener listener;
    private ReunionApiService mReunionApiService = DI.getService();

    public SalleFragmentRecyclerViewAdapter(List<Room> salleReunions, RecyclerViewClickListener listener) {
        this.mSalleReunions = salleReunions;
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
        Room meetingRoom = mSalleReunions.get(position);
        holder.mSalle.setText(meetingRoom.getName());
        holder.itemView.setOnClickListener(v -> {
            listener.onClick(v, holder.getAdapterPosition());
          //  mSalleReunions.add(mReunionApiService.getReunionsByPlace(meetingRoom.getName()));
        });

    }

    @Override
    public int getItemCount() {
        return mSalleReunions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mSalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSalle = itemView.findViewById(R.id.nomSalle);
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);

    }


}
