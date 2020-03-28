package ru.geekbrains.android1.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    static final String TOWN_KEY = "town_key";

    private TextView townTextView;
    private Button goOptionsSelectActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setOnTownSelectBtnClick();
    }

    private void initView() {
        townTextView = findViewById(R.id.townTextView);
        goOptionsSelectActivityBtn = findViewById(R.id.goOptionsSelectActivityBtn);
    }

    private void setOnTownSelectBtnClick() {
        goOptionsSelectActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, OptionsSelectActivity.class);
                intent.putExtra(TOWN_KEY, townTextView.getText().toString());
                startActivity(intent);
            }
        });
    }
}
