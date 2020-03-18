package ru.geekbrains.android1.hw1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SitySelect extends AppCompatActivity {

    private static final String TAG = "SitySelect";
    private CheckBox checkBoxAtmoPressure;
    private TextView textViewAtmoInfo;

    private CheckBox checkBoxWind;
    private TextView textViewWindInfo;

    private Button buttonTTTT;
    private TextView textViewTTTT;
    private final String counterDataKey = "counterDataKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sity_select);
        initViews();
        setOnСheckBoxAtmoPressure();
        setOnСheckBoxWind();

        initIncreaseBtnBehavior();

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
        checkBoxAtmoPressure = findViewById(R.id.checkBoxAtmoPressure);
        textViewAtmoInfo = findViewById(R.id.textViewAtmoInfo);

        checkBoxWind = findViewById(R.id.checkBoxWind);
        textViewWindInfo = findViewById(R.id.textViewWindInfo);

        buttonTTTT = findViewById(R.id.button_tt);
        textViewTTTT = findViewById(R.id.textView_TT);
    }

    private void initIncreaseBtnBehavior() {
        buttonTTTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counterValue = Integer.parseInt(textViewTTTT.getText().toString());
                String newTextValue = String.valueOf(++counterValue);
                textViewTTTT.setText(newTextValue);
            }
        });
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
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        //DataContainer container = (DataContainer)saveInstanceState.getSerializable(counterDataKey);

        String text = saveInstanceState.getString(counterDataKey, "0");
        textViewTTTT.setText(text);

        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        Toast.makeText(getApplicationContext(), "заебись - onSaveInstanceState()", Toast.LENGTH_SHORT).show();

        String text = textViewTTTT.getText().toString();
        saveInstanceState.putString(counterDataKey, text);

        super.onSaveInstanceState(saveInstanceState);

//        saveInstanceState.putSerializable(counterDataKey, DataContainer.getInstance());
    }
}
