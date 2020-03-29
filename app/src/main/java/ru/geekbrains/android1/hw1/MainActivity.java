package ru.geekbrains.android1.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    static final String TOWN_KEY = "town_key";
    static final String DEGREES_KEY = "degrees_key";
    static final String PRESSURE_KEY = "pressure_key";
    static final String WIND_KEY = "wind_key";
    private final static int REQUEST_CODE = 1;

    private TextView townTextView;
    private TextView degreesTextView;
    private TextView pressureTextView;
    private TextView windTextView;
    private boolean isPressureShow;
    private boolean isWindShow;
    private Button goOptionsSelectActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setOnGoOptionsSelectBtnClick();
    }

    private void initView() {
        townTextView = findViewById(R.id.townTextView);
        degreesTextView = findViewById(R.id.degreesTextView);
        pressureTextView = findViewById(R.id.pressureTextView);
        windTextView = findViewById(R.id.windTextView);
        goOptionsSelectActivityBtn = findViewById(R.id.goOptionsSelectActivityBtn);
    }

    private void setOnGoOptionsSelectBtnClick() {
        goOptionsSelectActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OptionsSelectActivity.class);
                intent.putExtra(TOWN_KEY, townTextView.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK) {
            townTextView.setText(data.getStringExtra(TOWN_KEY));
//            degreesTextView.setText(data.getStringExtra(DEGREES_KEY));
            isPressureShow = data.getBooleanExtra(PRESSURE_KEY, true);
            isWindShow = data.getBooleanExtra(WIND_KEY, true);
            if (isPressureShow) pressureTextView.setVisibility(View.VISIBLE); else pressureTextView.setVisibility(View.GONE);
            if (isWindShow) windTextView.setVisibility(View.VISIBLE); else windTextView.setVisibility(View.GONE);

        }
    }
}
