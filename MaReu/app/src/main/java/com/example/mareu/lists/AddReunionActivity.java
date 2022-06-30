package com.example.mareu.lists;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mareu.DI;
import com.example.mareu.R;
import com.example.mareu.methods.Reunion;
import com.example.mareu.methods.Room;
import com.example.mareu.model.ReunionApiService;
import com.example.mareu.model.RoomApiService;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class AddReunionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AppBarLayout mAppBarLayout;
    Toolbar mToolbar;
    Spinner mSpinnerRoom;
    EditText mDateTime;
    EditText mHourTime;
    TextInputEditText mTextInputEditTextSujet;
    TextInputEditText mTextInputEditTextParticipant;
    ExtendedFloatingActionButton mButtonAddReu;
    ReunionApiService mApiService = DI.getReunionService();
    RoomApiService mRoomApiService = DI.getRoomService();


    public static final String result = "nouvellereunion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reu);

        mAppBarLayout = findViewById(R.id.appBarAddReu);
        mToolbar = findViewById(R.id.toolBarAddReu);
        mSpinnerRoom = findViewById(R.id.spinnerRoom);
        mDateTime = findViewById(R.id.date_input);
        mHourTime = findViewById(R.id.time_input);
        mTextInputEditTextSujet = findViewById(R.id.textSujet);
        mTextInputEditTextParticipant = findViewById(R.id.ajoutParticipant);
        mButtonAddReu = findViewById(R.id.buttonAddReu);

        //calendrier et heure
        mDateTime.setOnClickListener(view -> showDateTimeDialog(mDateTime));
        mHourTime.setOnClickListener(view -> showTimeDialog(mHourTime));


        //retour en arrière avec la toolbar

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());

        //remplir le spinner

        mSpinnerRoom.setOnItemSelectedListener(this);

        List<String> roomNames = new ArrayList<>();
        for (Room room : mRoomApiService.getRooms()) {
            roomNames.add(room.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roomNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerRoom.setAdapter(adapter);


        //ajouter la réunion

        mButtonAddReu.setOnClickListener(view -> {

            Reunion newReunion = new Reunion(mTextInputEditTextSujet.getEditableText().toString(),
                    mDateTime.getEditableText().toString(),
                    mHourTime.getEditableText().toString(),
                    mRoomApiService.getRoomByName(mSpinnerRoom.getSelectedItem().toString()),
                    mTextInputEditTextParticipant.getEditableText().toString());
            mApiService.createReunion(newReunion);
            Intent intent = getIntent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        });


    }

    //affichage du timer
    private void showTimeDialog(EditText mHourTime) {
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"), Locale.FRANCE);
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hourOfDay, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(" HH:mm");
            mHourTime.setText(simpleDateFormat2.format(calendar.getTime()).toString());
        };
        //affichage timer
        new TimePickerDialog(AddReunionActivity.this, timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false)
                .show();
    }


    //affichage du calendrier

    private void showDateTimeDialog(EditText mDateTime) {
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"), Locale.FRANCE);

        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
            mDateTime.setText(simpleDateFormat1.format(calendar.getTime()));

        };
        //affichage calendrier
        new DatePickerDialog(AddReunionActivity.this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

}