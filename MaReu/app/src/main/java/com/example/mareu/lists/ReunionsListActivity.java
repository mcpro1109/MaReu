package com.example.mareu.lists;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.DI;
import com.example.mareu.R;
import com.example.mareu.methods.Reunion;
import com.example.mareu.methods.Room;
import com.example.mareu.model.ReunionApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReunionsListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private static RecyclerView recyclerView;
    private FloatingActionButton addReuFloatingButton;

    private static ReunionApiService reunionApiService = DI.getReunionService();
    private static List<Reunion> reunionList = new ArrayList<>();
    private static MaReuRecyclerViewAdapter adapter = new MaReuRecyclerViewAdapter(reunionApiService.getReunions(), (view, reunion) -> {
        reunionApiService.deleteReunion(reunion);
        refreshList();
    });

    int code = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mareu);

        //implémenter la recyclerview

        toolbar = findViewById(R.id.toolBar);
        recyclerView = findViewById(R.id.recyclerView);
        addReuFloatingButton = findViewById(R.id.addReu);
        toolbar.inflateMenu(R.menu.menu);

        //filtres selon date et lieu dans la recyclerview
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.filter_date:
                    dateDialog();
                    return true;
                case R.id.filter_room:
                    lieuReunion();
                    return true;
                case R.id.reset:
                    resetFilter();
                default:
                    return false;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        //ajouter une réunion
        addReuFloatingButton.setOnClickListener(view -> {
            Intent intent = new Intent(ReunionsListActivity.this, AddReunionActivity.class);
            startActivityForResult(intent, code);
        });
    }

    private void lieuReunion() {
        //ajouter le fragmentdialog
        RoomFragmentDialog roomFragmentDialog = new RoomFragmentDialog();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("rooms");
        roomFragmentDialog.show(ft, "rooms");
    }

    private void dateDialog() {
        Calendar cal = Calendar.getInstance();
        int selectedYear =cal.get(Calendar.YEAR);
        int selectedMonth = cal.get(Calendar.MONTH);
        int selectedDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            cal.set(year, month, day);
            adapter.update(reunionApiService.getReunionsFilteredByTime(cal.getTime()));
            //Log.d("filter", "dateDialog: " + cal.getTime());
        };

        // Create DatePickerDialog (Spinner Mode):
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);

        // Show
        datePickerDialog.show();
    }

    //initialiser la liste des réunions
    private static void refreshList() {
        adapter.update(reunionApiService.getReunions());
    }

    //methode appelée quand la réunion est ajoutée
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code) {
            if (resultCode == Activity.RESULT_OK) {
              //  Toast.makeText(ReunionsListActivity.this, "ajout de la réunion", Toast.LENGTH_SHORT).show();
                refreshList();
            }
        }
    }

    private void resetFilter() {
        refreshList();
    }

    public static void filterByRoom(Room room) {
        adapter.update(reunionApiService.getReunionsFilteredByRoom(room.getName()));
    }
}