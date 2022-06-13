package com.example.mareu.lists;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.R;
import com.example.mareu.methods.Reunion;
import com.example.mareu.methods.Room;
import com.example.mareu.model.ReunionApiService;

import java.util.List;

public class MaReuRecyclerViewAdapter extends RecyclerView.Adapter<MaReuRecyclerViewAdapter.ViewHolder> {

    private final List<Reunion> mReunions;
    private RecyclerViewClickListener listener;
    ReunionApiService mReunionApiService;
    Room room;


    public MaReuRecyclerViewAdapter(List<Reunion> reunions, RecyclerViewClickListener listener) {
        this.mReunions = reunions;
        this.listener = listener;

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
        String firstLine = reunion.getDate() + reunion.getHeure() + " \n" + reunion.getNomReunion() + " - " + reunion.getSalle().getName();
        SpannableStringBuilder text = new SpannableStringBuilder(firstLine + " \n" + reunion.getParticipants());

        text.setSpan(new StyleSpan(Typeface.ITALIC), 0, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new StyleSpan(Typeface.BOLD), 18, firstLine.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(new ForegroundColorSpan(Color.BLACK), 18, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        holder.mReunionDescription.setText(text);

        //affichage de l'image selon la salle
        Glide.with(holder.mImageViewAvatar.getContext())
                .load("https://ui-avatars.com/api/?name=" + reunion.getSalle().getName() + "&background=" + reunion.getSalle().getColorHex())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mImageViewAvatar);


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
        private ImageView mImageViewAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mReunionAvatar = itemView.findViewById(R.id.item_list_avatar);
            mReunionDescription = itemView.findViewById(R.id.item_list_name);
            mDelete = itemView.findViewById(R.id.item_list_delete_button);
            mImageViewAvatar = itemView.findViewById(R.id.item_list_avatar);
        }
    }

    public interface RecyclerViewClickListener {

        void onDelete(View view, Reunion reunion);
    }


}
