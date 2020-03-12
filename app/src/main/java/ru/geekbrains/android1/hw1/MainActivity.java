package ru.geekbrains.android1.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Switch switchEnRu;
    private TextView regionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initViews() {
        regionTextView = findViewById(R.id.textView6);
        switchEnRu = findViewById(R.id.switch1);

    }


    private void setOnSwitchChanged() {

            switchEnRu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String text;
                    if(isChecked) {
                        text = getString(R.string.region);
                    } else {
                        text = getString(R.string.region_en);
                    }
                    regionTextView.setText(text);
                }
            });
        }

}
