package com.example.mareu.lists;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.methods.Reunion;
import com.example.mareu.model.ReunionApiService;
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
    private ReunionApiService mReunionApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mareu);

        //implémenter la recyclerview
        mAppBarLayout = findViewById(R.id.appBar);
        mToolbar = findViewById(R.id.toolBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAddReu = findViewById(R.id.addReu);

        mReunionApiService = DI.getService();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //MaReuRecyclerViewAdapter adapter = new MaReuRecyclerViewAdapter(mReunion);
        mRecyclerView.setAdapter(adapter);


        //ajouter une réunion
        mAddReu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReunionsListActivity.this, AddReunionActivity.class);
                startActivity(intent);
            }
        });


    }


    //initialiser la liste des réunions
    private void initList() {
        mReunion = mReunionApiService.getReunions();
        mRecyclerView.setAdapter(new MaReuRecyclerViewAdapter(mReunion, new MaReuRecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onDelete(View view, Reunion reunion) {
                mReunionApiService.deleteReunion(reunion);
                initList();
            }
        }));
    }



    @Override
    public void onResume() {
        super.onResume();
        initList();
    }
}