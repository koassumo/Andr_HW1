package ru.geekbrains.android1.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpInstructionActivity extends AppCompatActivity {

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_instruction);

        initView();
        setOnButtonBackClick();
    }

    private void initView() {
        buttonBack = findViewById(R.id.goBackToMainActivityBtn);

    }

    private void setOnButtonBackClick() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
