package ru.geekbrains.android1.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpInstructionActivity extends AppCompatActivity {

    private Button goBackToOptionsSelectActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_instruction);

        initView();
        setOnGoBackToOptionsSelectActivityBtnClick();
    }

    private void initView() {
        goBackToOptionsSelectActivityBtn = findViewById(R.id.goBackMainActivityBtn);
    }

    private void setOnGoBackToOptionsSelectActivityBtnClick() {
        goBackToOptionsSelectActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
