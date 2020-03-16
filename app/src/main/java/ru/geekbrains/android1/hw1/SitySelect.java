package ru.geekbrains.android1.hw1;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SitySelect extends AppCompatActivity {

    private CheckBox checkBoxAtmoPressure;
    private TextView textViewAtmoInfo;

    private CheckBox checkBoxWind;
    private TextView textViewWindInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sity_select);
        initViews();
        setOnСheckBoxAtmoPressure();
        setOnСheckBoxWind();
    }

    private void initViews() {
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

}
