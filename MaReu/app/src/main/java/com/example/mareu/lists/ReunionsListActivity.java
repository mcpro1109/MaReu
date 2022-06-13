package com.example.mareu.lists;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.methods.Reunion;
import com.example.mareu.model.ReunionApiService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class ReunionsListActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddReu;
    private List<Reunion> mReunion;
    MaReuRecyclerViewAdapter adapter;
    private ReunionApiService mReunionApiService = DI.getService();

    int code = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mareu);

        //implémenter la recyclerview
        mAppBarLayout = findViewById(R.id.appBar);
        mToolbar = findViewById(R.id.toolBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAddReu = findViewById(R.id.addReu);
        mToolbar.inflateMenu(R.menu.menu);

        //filtres selon date et lieu dans la recyclerview
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.filter_date:
                        dateDialog();
                        return true;
                    case R.id.filter_salle:
                        lieuReunion();
                        return true;
                    case R.id.reset:
                        resetFilter();
                    default:
                        return false;
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);

        //ajouter une réunion
        mAddReu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReunionsListActivity.this, AddReunionActivity.class);
                startActivityForResult(intent, code);
            }
        });
        initList();
    }


    private void resetFilter() {
         mReunion.clear();
        Log.d("reset", "bouton reset");
        mReunion.addAll(mReunionApiService.getReunions());
        Log.d("reset2", "mmm");
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void lieuReunion() {
     /*  mReunion.addAll(mReunionApiService.getReunionsByPlace());
        mRecyclerView.getAdapter().notifyDataSetChanged();*/
        SalleFragmentDialog salleFragmentDialog = new SalleFragmentDialog();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("salles");
       /* if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);*/

        salleFragmentDialog.show(ft, "salles");
    }


    private void dateDialog() {
        int selectedYear = 2022;
        int selectedMonth = 5;
        int selectedDayOfMonth = 1;

// Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                mReunion.clear();
                Log.d("date1", "date=" + cal);
               mReunion.addAll(mReunionApiService.getReunionsFilteredByTime(cal.getTime()));
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

        };

// Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

// Show
        datePickerDialog.show();

    }

    //initialiser la liste des réunions
    private void initList() {
        mReunion = mReunionApiService.getReunions();
        mRecyclerView.setAdapter(new MaReuRecyclerViewAdapter(mReunion, (view, reunion) -> {
            mReunionApiService.deleteReunion(reunion);
            initList();
        }));
    }

    //methode appelée quand la réunion est ajoutée
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(ReunionsListActivity.this, "ajout de la réunion", Toast.LENGTH_SHORT);
                initList();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(ReunionsListActivity.this, "annulation", Toast.LENGTH_SHORT);
            }
        }
    }


}