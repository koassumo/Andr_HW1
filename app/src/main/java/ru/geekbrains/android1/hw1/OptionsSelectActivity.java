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

    private static final String TAG = "OptionsSelect";
    private final String TOWN_EDIT_TEXT_STATE_KEY = "TOWN_EDIT_TEXT_STATE_KEY";
    private final String TOWN_SPINNER_STATE_KEY = "TOWN_SPINNER_STATE_KEY";
    private final String PRESSURE_IS_CHECKED_STATE_KEY = "PRESSURE_IS_CHECKED_STATE_KEY";
    private final String WIND_IS_CHECKED_STATE_KEY = "WIND_IS_CHECKED_STATE_KEY";

    private Button goBackMainActivityBtn;
    private Button goHelpInstructionActivityBtn;

    private TextView townSelectEditView;
    private Spinner townSelectSpinner;
    private CheckBox pressureCheckBox, windCheckBox;
    private TextView pressureTextView, windTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_select);
        initViews();
        setDataFromMainActivity();

        setOnPressureCheckBoxClick();
        setOnWindCheckBoxClick();

        setOnGoBackMainActivityBtnClick();
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

    private void setDataFromMainActivity() {
        pressureTextView.setText(getIntent().getStringExtra(Constants.PRESSURE_DATA_KEY));
        if (getIntent().getBooleanExtra(Constants.PRESSURE_IS_CHECKED_KEY, true))
            pressureTextView.setVisibility(View.VISIBLE);
        else pressureTextView.setVisibility(View.GONE);
    }

    private void initViews() {
        goBackMainActivityBtn = findViewById(R.id.goBackMainActivityBtn);
        goHelpInstructionActivityBtn = findViewById(R.id.goHelpInstructionActivityBtn);

        townSelectEditView = findViewById(R.id.townSelectEditView);
        String text = getIntent().getStringExtra(Constants.TOWN_DATA_KEY);
        townSelectEditView.setText(text);

        townSelectSpinner = findViewById(R.id.townSelectSpinner);

        pressureCheckBox = findViewById(R.id.pressureCheckBox);
        pressureTextView = findViewById(R.id.pressureTextView);

        windCheckBox = findViewById(R.id.windCheckBox);
        windTextView = findViewById(R.id.windTextView);
    }

    private void setOnGoBackMainActivityBtnClick() {
        goBackMainActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent();
                intentResult.putExtra(Constants.TOWN_DATA_KEY, townSelectEditView.getText().toString());
                intentResult.putExtra(Constants.PRESSURE_IS_CHECKED_KEY, pressureCheckBox.isChecked());
                intentResult.putExtra(Constants.WIND_IS_CHECKED_KEY, windCheckBox.isChecked());
                setResult(MainActivity.RESULT_OK, intentResult);
                finish();
            }
        });
    }




    private void setOnHelpBtnClick() {
        goHelpInstructionActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (OptionsSelectActivity.this, HelpInstructionActivity.class);
//                intent.putExtra(MainActivity.TOWN_KEY, townSelectEditView.getText().toString());    если нужно что-то передать
                startActivity(intent);
            }
        });
    }

    private void setOnPressureCheckBoxClick() {
        pressureCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) pressureTextView.setVisibility(View.VISIBLE); else pressureTextView.setVisibility(View.GONE);
            }
        });
    }

    private void setOnWindCheckBoxClick() {
        windCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) windTextView.setVisibility(View.VISIBLE); else windTextView.setVisibility(View.GONE);
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

        saveInstanceState.putString(TOWN_EDIT_TEXT_STATE_KEY, townSelectEditView.getText().toString());
        saveInstanceState.putInt(TOWN_SPINNER_STATE_KEY, townSelectSpinner.getSelectedItemPosition());
        saveInstanceState.putBoolean(PRESSURE_IS_CHECKED_STATE_KEY, pressureCheckBox.isChecked());
        saveInstanceState.putBoolean(WIND_IS_CHECKED_STATE_KEY, windCheckBox.isChecked());

        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);

        townSelectEditView.setText(saveInstanceState.getString(TOWN_EDIT_TEXT_STATE_KEY));
        townSelectSpinner.setSelection(saveInstanceState.getInt(TOWN_SPINNER_STATE_KEY));
        pressureCheckBox.setChecked(saveInstanceState.getBoolean(PRESSURE_IS_CHECKED_STATE_KEY));
        windCheckBox.setChecked(saveInstanceState.getBoolean(WIND_IS_CHECKED_STATE_KEY));

        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Повторный запуск!! - onRestoreInstanceState()");
    }
}
