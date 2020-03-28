package ru.geekbrains.android1.hw1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class OptionsSelectActivity extends AppCompatActivity {


    private static final String TAG = "SitySelect";
    private Button buttonBack;
    private Button buttonHelp;

    private TextView editTextTown;
    private Spinner spinnerTown;
    private CheckBox checkBoxAtmoPressure, checkBoxWind;
    private TextView textViewAtmoInfo, textViewWindInfo;

    private final String editTextTownKey = "editTextTownKey";
    private final String spinnerTownKey = "spinnerTownKey";
    private final String checkAtmoKey = "checkAtmoKey";
    private final String checkWindKey = "checkWindKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_select);
        initViews();
        setOnCheckBoxAtmoPressure();
        setOnCheckBoxWind();

        setOnStartMainActivityBtnClick ();
        setOnHelpBtnClick();

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            instanceState = "Повторный запуск!";
        }
        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, instanceState + " - onCreate()");
    }

    private void initViews() {
        buttonBack = findViewById(R.id.goBackToMainActivityBtn);
        buttonHelp = findViewById(R.id.goHelpInstructionActivityBtn);

        editTextTown = findViewById(R.id.townSelectEditView);
        String text = getIntent().getStringExtra(MainActivity.TOWN_KEY);
        editTextTown.setText(text);

        spinnerTown = findViewById(R.id.townSelectSpinner);

        checkBoxAtmoPressure = findViewById(R.id.pressureCheckBox);
        textViewAtmoInfo = findViewById(R.id.pressureTextView);

        checkBoxWind = findViewById(R.id.windCheckBox);
        textViewWindInfo = findViewById(R.id.windTextView);
    }

    private void setOnStartMainActivityBtnClick() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (OptionsSelectActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setOnHelpBtnClick() {
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (OptionsSelectActivity.this, HelpInstructionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setOnCheckBoxAtmoPressure() {
        checkBoxAtmoPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String text;
                if (isChecked) {
                    text = getString(R.string.atmo_pressure);
                } else {
                    text = getString(R.string.atmo_pressure_null);
                }
                textViewAtmoInfo.setText(text);
            }
        });
    }

    private void setOnCheckBoxWind() {
        checkBoxWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String text;
                if (isChecked) {
                    text = getString(R.string.wind);
                } else {
                    text = getString(R.string.wind_null);
                }
                textViewWindInfo.setText(text);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onSaveInstanceState()");

        saveInstanceState.putString(editTextTownKey, editTextTown.getText().toString());
        saveInstanceState.putInt(spinnerTownKey, spinnerTown.getSelectedItemPosition());
        saveInstanceState.putBoolean(checkAtmoKey, checkBoxAtmoPressure.isChecked());
        saveInstanceState.putBoolean(checkWindKey, checkBoxWind.isChecked());

        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        editTextTown.setText(saveInstanceState.getString(editTextTownKey));
        spinnerTown.setSelection(saveInstanceState.getInt(spinnerTownKey));
        checkBoxAtmoPressure.setChecked(saveInstanceState.getBoolean(checkAtmoKey));
        checkBoxWind.setChecked(saveInstanceState.getBoolean(checkWindKey));

        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Повторный запуск!! - onRestoreInstanceState()");
    }
}
