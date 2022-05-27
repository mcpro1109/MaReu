package com.example.mareu.lists;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.methods.Reunion;
import com.example.mareu.model.ReunionApiService;

import java.util.List;

public class MaReuRecyclerViewAdapter extends RecyclerView.Adapter<MaReuRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;
    private RecyclerViewClickListener listener;
    ReunionApiService mReunionApiService;

    public MaReuRecyclerViewAdapter(List<Reunion> reunions, RecyclerViewClickListener listener) {
        this.mReunions = reunions;
        this.listener=listener;

    }


    @Override
    public MaReuRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaReuRecyclerViewAdapter.ViewHolder holder, int position) {
        //envoyer les infos des réunions dans la recyclerview et mise en page
        Reunion reunion = mReunions.get(position);
        String firstLine=reunion.getNomReunion() + " - " + reunion.getHeure() + " - " + reunion.getSalle();
        SpannableStringBuilder text = new SpannableStringBuilder( firstLine + " \n" + reunion.getParticipants());
        text.setSpan(new StyleSpan(Typeface.BOLD), 0, firstLine.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.mReunionDescription.setText(text);

       // supprimer une réunion
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
            mReunionAvatar = itemView.findViewById(R.id.item_list_avatar);
            mReunionDescription = itemView.findViewById(R.id.item_list_name);

            mDelete = itemView.findViewById(R.id.item_list_delete_button);
        }
    }

    public interface RecyclerViewClickListener {

        void onDelete(View view, Reunion reunion);
    }

}
