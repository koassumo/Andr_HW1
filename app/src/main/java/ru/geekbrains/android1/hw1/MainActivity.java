package ru.geekbrains.android1.hw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 1;

    private TextView townTextView;
    private TextView degreesTextView;
    private TextView pressureTextView;
    private TextView windTextView;
    private TextView skyTextView;
    private boolean isPressureShow = true;
    private boolean isWindShow = true;
    private MaterialButton goOptionsSelectActivityBtn;

    private final Handler handler = new Handler();
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFonts();
        setOnGoOptionsSelectBtnClick();
        updateWeatherData ("Moscow");
    }

    private void initView() {
        townTextView = findViewById(R.id.townTextView);
        degreesTextView = findViewById(R.id.degreesTextView);
        pressureTextView = findViewById(R.id.pressureTextView);
        windTextView = findViewById(R.id.windTextView);
        skyTextView = findViewById(R.id.skyTextView);
        goOptionsSelectActivityBtn = findViewById(R.id.goOptionsSelectActivityBtn);
        updateWeatherData ("Moscow");
    }


    private void initFonts() {
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weather.ttf");
        skyTextView.setTypeface(weatherFont);
    }



    private void updateWeatherData(final String city) {
        new Thread() {
            @Override
            public void run() {
                degreesTextView.setText("0");
                final JSONObject jsonObject = getJSONData(city);
                if(jsonObject == null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), R.string.place_not_found,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            renderWeather(jsonObject);
                        }
                    });
                }
            }
        }.start();
    }


    private static final String OPEN_WEATHER_API_KEY = "762ee61f52313fbd10a4eb54ae4d4de2";
    private static final String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String KEY = "x-api-key";

    JSONObject getJSONData(String city) {
        degreesTextView.setText("5");
        try {
            URL url = new URL(String.format(OPEN_WEATHER_API_URL, city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty(KEY, OPEN_WEATHER_API_KEY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder rawData = new StringBuilder(1024);
            String tempVariable;

            while ((tempVariable = reader.readLine()) != null) {
                rawData.append(tempVariable).append("\n");
            }

            reader.close();

            JSONObject jsonObject = new JSONObject(rawData.toString());
            if(jsonObject.getInt("cod") != 200) {
                return null;
            } else {
                return jsonObject;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }
    }









    private void renderWeather(JSONObject jsonObject) {
        Log.d(LOG_TAG, "json: " + jsonObject.toString());
        Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
        try {
            JSONObject details = jsonObject.getJSONArray("weather").getJSONObject(0);
            JSONObject main = jsonObject.getJSONObject("main");

            setPlaceName(jsonObject);
            setDetails(details, main);
            setCurrentTemp(main);
            setUpdatedText(jsonObject);
            setWeatherIcon(details.getInt("id"),
                    jsonObject.getJSONObject("sys").getLong("sunrise") * 1000,
                    jsonObject.getJSONObject("sys").getLong("sunset") * 1000);
        } catch (Exception exc) {
            exc.printStackTrace();
            Log.e(LOG_TAG, "One or more fields not found in the JSON data");
        }
        Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_LONG).show();
    }

    private void setPlaceName(JSONObject jsonObject) throws JSONException {
        String cityText = jsonObject.getString("name").toUpperCase() + ", "
                + jsonObject.getJSONObject("sys").getString("country");
        townTextView.setText(cityText);
    }

    private void setDetails(JSONObject details, JSONObject main) throws JSONException {
        String detailsText = details.getString("description").toUpperCase() + "\n"
                + "Humidity: " + main.getString("humidity") + "%" + "\n"
                + "Pressure: " + main.getString("pressure") + "hPa";
        pressureTextView.setText(detailsText);
    }

    private void setCurrentTemp(JSONObject main) throws JSONException {
        String currentTextText = String.format(Locale.getDefault(), "%.2f",
                main.getDouble("temp")) + "\u2103";
        degreesTextView.setText(currentTextText);
    }

    private void setUpdatedText(JSONObject jsonObject) throws JSONException {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String updateOn = dateFormat.format(new Date(jsonObject.getLong("dt") * 1000));
        String updatedText = "Last update: " + updateOn;
        windTextView.setText(updatedText);
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";

        if(actualId == 800) {
            long currentTime = new Date().getTime();
            if(currentTime >= sunrise && currentTime < sunset) {
                icon = "\u2600";
                //icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2: {
                    icon = getString(R.string.weather_thunder);
                    break;
                }
                case 3: {
                    icon = getString(R.string.weather_drizzle);
                    break;
                }
                case 5: {
                    icon = getString(R.string.weather_rainy);
                    break;
                }
                case 6: {
                    icon = getString(R.string.weather_snowy);
                    break;
                }
                case 7: {
                    icon = getString(R.string.weather_foggy);
                    break;
                }
                case 8: {
                    icon = "\u2601";
                    // icon = getString(R.string.weather_cloudy);
                    break;
                }
            }
        }
        skyTextView.setText(icon);
    }







    private void setOnGoOptionsSelectBtnClick() {
        goOptionsSelectActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OptionsSelectActivity.class);
                intent.putExtra(Constants.TOWN_DATA_KEY, townTextView.getText().toString());
                intent.putExtra(Constants.PRESSURE_IS_CHECKED_KEY, isPressureShow);
                intent.putExtra(Constants.WIND_IS_CHECKED_KEY, isWindShow);
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
            townTextView.setText(data.getStringExtra(Constants.TOWN_DATA_KEY));
            isPressureShow = data.getBooleanExtra(Constants.PRESSURE_IS_CHECKED_KEY, true);
            isWindShow = data.getBooleanExtra(Constants.WIND_IS_CHECKED_KEY, true);
            if (isPressureShow) pressureTextView.setVisibility(View.VISIBLE); else pressureTextView.setVisibility(View.GONE);
            if (isWindShow) windTextView.setVisibility(View.VISIBLE); else windTextView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState){
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        saveInstanceState.putString(Constants.TOWN_DATA_KEY, townTextView.getText().toString());
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        townTextView.setText(saveInstanceState.getString(Constants.TOWN_DATA_KEY));
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
    }
}
