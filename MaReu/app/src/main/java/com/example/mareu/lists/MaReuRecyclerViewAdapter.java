package com.example.mareu.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.methods.Reunion;

import java.util.List;

public class MaReuRecyclerViewAdapter extends RecyclerView.Adapter<MaReuRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;
    private RecyclerViewClickListener listener;

    public MaReuRecyclerViewAdapter(List<Reunion> reunions) {
        mReunions = reunions;

    }


    @Override
    public MaReuRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaReuRecyclerViewAdapter.ViewHolder holder, int position) {
        Reunion reunion = mReunions.get(position);
        holder.mReunionDescription.setText(reunion.getNomReunion());

        holder.mDelete.setOnClickListener(view -> listener.onDelete(view, reunion));
    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mReunionAvatar;
        public TextView mReunionDescription;
        public ImageView mDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        mReunionAvatar=itemView.findViewById(R.id.item_list_avatar);
        mReunionDescription=itemView.findViewById(R.id.item_list_name);
        mDelete=itemView.findViewById(R.id.item_list_delete_button);
        }
    }

    public interface RecyclerViewClickListener {

        void onDelete(View view, Reunion reunion);
    }

}
