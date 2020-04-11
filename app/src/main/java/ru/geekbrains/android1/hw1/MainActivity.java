package ru.geekbrains.android1.hw1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 1;

    private TextView townTextView;
    private TextView temperatureTextView;
    private TextView pressureTextView;
    private TextView windTextView;
    private TextView skyTexView;
    private ImageView skyImageView;
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
        updateWeatherData(townTextView.getText().toString());
//        skyImageView.setImageResource(R.drawable.clear_sky_white);
    }

    private void initView() {
        townTextView = findViewById(R.id.townTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        pressureTextView = findViewById(R.id.pressureTextView);
        windTextView = findViewById(R.id.windTextView);
        skyTexView = findViewById(R.id.skyTextView);
        skyImageView = findViewById(R.id.skyImageView);
        goOptionsSelectActivityBtn = findViewById(R.id.goOptionsSelectActivityBtn);
    }

    private void initFonts(){
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weather.ttf");
        skyTexView.setTypeface(weatherFont);
    }

    private void updateWeatherData(final String city) {
        new Thread() {
            @Override
            public void run() {
                final JSONObject jsonObject = WeatherDataLoader.getJSONData(city);
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

    private void renderWeather(JSONObject jsonObject) {
        Log.d(LOG_TAG, "json: " + jsonObject.toString());
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
    }

    private void setPlaceName(JSONObject jsonObject) throws JSONException {
        String cityText = jsonObject.getString("name").toUpperCase() + ", "
                + jsonObject.getJSONObject("sys").getString("country");
        townTextView.setText(cityText);
    }

    private void setDetails(JSONObject details, JSONObject main) throws JSONException {
        String detailsText = details.getString("description").toUpperCase() + "\n" + main.getString("pressure") + "hPa";
        pressureTextView.setText(detailsText);
    }

    private void setCurrentTemp(JSONObject main) throws JSONException {
        String currentTextText = String.format(Locale.getDefault(), "%.2f", main.getDouble("temp"));
        temperatureTextView.setText(currentTextText);
    }

    private void setUpdatedText(JSONObject jsonObject) throws JSONException {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String updateOn = dateFormat.format(new Date(jsonObject.getLong("dt") * 1000));
        String updatedText = "Last update: " + updateOn;
        windTextView.setText("100");
    }

    private void setWeatherIcon(int id, long sunrise, long sunset) {
        String icon = "";
        String skyPictureName = "snow_white";

        switch (id / 100) {
            case 2: {
                icon = getString(R.string.weather_thunder); skyPictureName = "thunder_white";
                break;
            }
            case 3: {
                if (id < 302) {
                    icon = getString(R.string.weather_drizzle); skyPictureName = "rain_light_white";
                } else {
                    icon = getString(R.string.weather_drizzle); skyPictureName = "rain_shower_white";
                }
                break;
            }
            case 5: {
                if (id < 502) {
                    icon = getString(R.string.weather_rainy); skyPictureName = "rain_light_white";
                } else {
                    icon = getString(R.string.weather_rainy); skyPictureName = "rain_shower_white";
                }
                break;
            }
            case 6: {
                icon = getString(R.string.weather_snowy); skyPictureName = "snow_white";
                break;
            }
            case 7: {
                icon = getString(R.string.weather_foggy); skyPictureName = "foggy_white";
                break;
            }
            case 8: {
                if (id > 802) {
                    icon = getString(R.string.weather_foggy); skyPictureName = "cloud_overcast_white";
                    break;
                }
                if (id == 802) {
                    icon = getString(R.string.weather_foggy); skyPictureName = "cloud_broken_white";
                    break;
                }
                if (id == 801 ) {
                    long currentTime = new Date().getTime();
                    if(currentTime >= sunrise && currentTime < sunset) {
                        icon = "\u2600"; skyPictureName = "cloud_few_white";
                    } else {
                        icon = getString(R.string.weather_clear_night); skyPictureName = "night_cloud_few_white";
                    }
                    break;
                }
                if (id == 800 ) {
                    long currentTime = new Date().getTime();
                    if(currentTime >= sunrise && currentTime < sunset) {
                        icon = "\u2600"; skyPictureName = "clear_sky_white";
                    } else {
                        icon = getString(R.string.weather_clear_night); skyPictureName = "night_clear_sky_white";
                    }
                }
            }
        }
        skyTexView.setText(id + " ww " + icon);
        int idPicture = getResources().getIdentifier(skyPictureName, "drawable", getPackageName());
        skyImageView.setImageResource(idPicture);
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
            updateWeatherData(townTextView.getText().toString());
        }
        updateWeatherData(townTextView.getText().toString());
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
