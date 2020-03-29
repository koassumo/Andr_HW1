package ru.geekbrains.android1.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpInstructionActivity extends AppCompatActivity {

    private Button goBackToOptionsSelectActivityBtn;
    private Button goUrlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_instruction);

        initView();
        setOnGoBackToOptionsSelectActivityBtnClick();
        setOnGoUrlBtnClick();
    }

    private void initView() {
        goBackToOptionsSelectActivityBtn = findViewById(R.id.goBackMainActivityBtn);
        goUrlBtn = findViewById(R.id.goUrlButton);
    }

    private void setOnGoBackToOptionsSelectActivityBtnClick() {
        goBackToOptionsSelectActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setOnGoUrlBtnClick() {
        goUrlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urlText = "https://yandex.ru/pogoda/";
                Uri uri = Uri.parse(urlText);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browserIntent);
            }
        });
    }

}
