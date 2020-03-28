package ru.geekbrains.android1.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    static final String TOWN_KEY = "town_key";

    private TextView textViewSity;
    private Button buttonSitySelectActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setOnBtnSitySelect();
    }

    private void initView() {
        textViewSity = findViewById(R.id.townTextView);
        buttonSitySelectActivity = findViewById(R.id.goOptionsSelectActivityBtn);
    }

    private void setOnBtnSitySelect() {
        buttonSitySelectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, OptionsSelectActivity.class);
                intent.putExtra(TOWN_KEY, textViewSity.getText().toString());
                startActivity(intent);
            }
        });
    }
}
