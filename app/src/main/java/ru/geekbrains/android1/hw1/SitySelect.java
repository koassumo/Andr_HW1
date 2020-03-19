package ru.geekbrains.android1.hw1;

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

public class SitySelect extends AppCompatActivity {

    private static final String TAG = "SitySelect";
    private TextView editTextTown;
    private Spinner spinnerTown;
    private CheckBox checkBoxAtmoPressure;
    private TextView textViewAtmoInfo;

    private CheckBox checkBoxWind;
    private TextView textViewWindInfo;

    private final String editTextTownKey = "editTextTownKey";
    private final String spinnerTownKey = "spinnerTownKey";
    private final String checkAtmoKey = "checkAtmoKey";
    private final String checkWindKey = "checkWindKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sity_select);
        initViews();
        setOnСheckBoxAtmoPressure();
        setOnСheckBoxWind();

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
        editTextTown = findViewById(R.id.textViewSitySelectChoose);
        spinnerTown = findViewById(R.id.spinnerTown);

        checkBoxAtmoPressure = findViewById(R.id.checkBoxAtmoPressure);
        textViewAtmoInfo = findViewById(R.id.textViewAtmoInfo);

        checkBoxWind = findViewById(R.id.checkBoxWind);
        textViewWindInfo = findViewById(R.id.textViewWindInfo);
    }

    private void setOnСheckBoxAtmoPressure() {
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

    private void setOnСheckBoxWind() {
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
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onSaveInstanceState()");

        saveInstanceState.putString(editTextTownKey, editTextTown.toString());
        saveInstanceState.putString(spinnerTownKey, (String) spinnerTown.getSelectedItem());
        saveInstanceState.putBoolean(checkAtmoKey, checkBoxAtmoPressure.isChecked());
        saveInstanceState.putBoolean(checkWindKey, checkBoxWind.isChecked());

        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        editTextTown.setText(saveInstanceState.getString(editTextTownKey)); // выдает странности после поворота
        // spinnerTown. ---- не нашел set метод ----- :(     (saveInstanceState.getString(spinnerTownKey));
        checkBoxAtmoPressure.setChecked(saveInstanceState.getBoolean(checkAtmoKey));
        checkBoxWind.setChecked(saveInstanceState.getBoolean(checkWindKey));

        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Повторный запуск!! - onRestoreInstanceState()");
    }
}
