package com.example.mareu.lists;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.methods.Reunion;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ReunionsListActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddReu;
    private List<Reunion> mReunion;
    MaReuRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mareu);

            //implémenter la recyclerview
        mAppBarLayout = findViewById(R.id.appBar);
        mToolbar = findViewById(R.id.toolBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAddReu = findViewById(R.id.addReu);

       // mAddReu.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(ReunionsListActivity.this, R.color.white)));

      // MaReuRecyclerViewAdapter adapter=new MaReuRecyclerViewAdapter(mReunion);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);


        //ajouter une réunion
        mAddReu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReunionsListActivity.this, AddReu.class);
                startActivity(intent);
            }
        });
    }


}