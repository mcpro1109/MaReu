package com.example.mareu.lists;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mareu.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;

public class AddReu extends AppCompatActivity {

    AppBarLayout mAppBarLayout;
    Toolbar mToolbar;
    Spinner mSpinnerSalle;
    TextInputEditText mTextInputEditTextSujet;
    TextInputEditText mTextInputEditTextParticipant;
    Button mButtonAddReu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reu);

        mAppBarLayout = findViewById(R.id.appBarAddReu);
        mToolbar = findViewById(R.id.toolBarAddReu);
        mSpinnerSalle = findViewById(R.id.spinnerSalle);
        mTextInputEditTextSujet = findViewById(R.id.textSujet);
        mTextInputEditTextParticipant = findViewById(R.id.ajoutParticipant);
        mButtonAddReu = findViewById(R.id.addReu);


        //retour en arrière avec la toolbar

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
    }
}