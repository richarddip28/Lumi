package arkstudios.lumiapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class BabyMenu extends AppCompatActivity {

    public ImageButton menu, settings, color, baby, notifications, timer;
    Intent nextScreen;
    Animation fadeIn, fadeOut;
    TextView babyTitle,tf1,tf2,tf3,tf4,tf5,tf6;
    MediaPlayer mp;
    Spinner songList;
    SensorManager sensorManager;
    Sensor sensor;
    VibrationEffect vibe;
    Vibrator v;
    Switch s;
    TextView light;
    ArrayAdapter adapter;
    Typeface custom_font;
    ArrayList<String> chatList;
    Set<String> set;
    Date d;
    SimpleDateFormat sdf;
    String currentTime;

    public void setFont(){

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Black.ttf");

        babyTitle.setTypeface(custom_font);
        tf1.setTypeface(custom_font);
        tf2.setTypeface(custom_font);
        tf3.setTypeface(custom_font);
        tf4.setTypeface(custom_font);
        tf5.setTypeface(custom_font);
        tf6.setTypeface(custom_font);


    }

    public void init(){

        sdf = new SimpleDateFormat("hh:mm a");

        menu = (ImageButton) findViewById(R.id.imageButton5);
        settings = (ImageButton) findViewById(R.id.imageButton7);
        color = (ImageButton) findViewById(R.id.imageButton6);
        baby = (ImageButton) findViewById(R.id.imageButton8);
        notifications = (ImageButton) findViewById(R.id.imageButton9);
        timer = (ImageButton) findViewById(R.id.imageButton3);
        fadeIn = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        babyTitle = (TextView) findViewById(R.id.babyTitle);
        tf1 = (TextView) findViewById(R.id.textView4);
        tf2 = (TextView) findViewById(R.id.textView5);
        tf3 = (TextView) findViewById(R.id.textView6);
        tf4 = (TextView) findViewById(R.id.textView7);
        tf5 = (TextView) findViewById(R.id.textView3);
        tf6 = (TextView) findViewById(R.id.textView8);

        setFont();


        songList = (Spinner) findViewById(R.id.spinner_song);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        s = (Switch) findViewById(R.id.switch1);
        light = (TextView) findViewById(R.id.light);
        adapter = ArrayAdapter.createFromResource(this, R.array.song_list, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        songList.setAdapter(adapter);

    }

    private void vibratePhone() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(150);
        }
    }



    public void menuButtonClicked(View view){

        if (settings.getVisibility() == View.INVISIBLE) {

            //settings.setVisibility(View.VISIBLE);
            settings.startAnimation(fadeIn);
            settings.setVisibility(View.VISIBLE);
            color.startAnimation(fadeIn);
            color.setVisibility(View.VISIBLE);
            baby.startAnimation(fadeIn);
            baby.setVisibility(View.VISIBLE);
            notifications.startAnimation(fadeIn);
            notifications.setVisibility(View.VISIBLE);
            timer.startAnimation(fadeIn);
            timer.setVisibility(View.VISIBLE);
        }//end if
        else {

            settings.startAnimation(fadeOut);
            settings.setVisibility(View.INVISIBLE);
            color.startAnimation(fadeOut);
            color.setVisibility(View.INVISIBLE);
            baby.startAnimation(fadeOut);
            baby.setVisibility(View.INVISIBLE);
            notifications.startAnimation(fadeOut);
            notifications.setVisibility(View.INVISIBLE);
            timer.startAnimation(fadeOut);
            timer.setVisibility(View.INVISIBLE);
        }//end else

    }

    public void settingsButtonClicked(View view){
        nextScreen = new Intent(this, SettingsMenu.class);
        startActivity(nextScreen);
    }
    public void colorButtonClicked(View view){
        nextScreen = new Intent(this, ColorMenu.class);
        startActivity(nextScreen);
    }
    public void babyButtonClicked(View view){
        Toast.makeText(this, "You are already here!",
                Toast.LENGTH_LONG).show();
    }
    public void notificationsButtonClicked(View view){
        nextScreen = new Intent(this, MessageBoardScreen.class);
        startActivity(nextScreen);
    }

    public void timerButtonClicked(View view){
        nextScreen = new Intent(this, TimerMenu.class);
        startActivity(nextScreen);
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(lightListener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(lightListener);
    }

    public SensorEventListener lightListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];

            light.setText((int)x + " lux");
            d = new Date();
            currentTime = sdf.format(d);


            if((int)x<5000 && s.isChecked()) {
                vibratePhone();
            }
            if((int)x>=20000 && s.isChecked()){
                vibratePhone();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_menu);
        init();

        songList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    switch(i){

                        case 1:
                            if (mp != null) {
                                mp.release();
                                mp = null;
                            }
                            mp = MediaPlayer.create(BabyMenu.this, R.raw.rock_a_bye_baby);
                            mp.start();
                            break;
                        case 2:
                            if (mp != null) {
                                mp.release();
                                mp = null;
                            }
                            mp = MediaPlayer.create(BabyMenu.this, R.raw.twinkle_twinkle);
                            mp.start();
                            break;
                        default:
                            return;
                    }//end switch
                }//end onSelected

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

            s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(s.isChecked()){
                        light.setVisibility(View.VISIBLE);
                    }
                    else{
                        light.setVisibility(View.INVISIBLE);
                    }

                }
            });




    }//end onCreate()
}//end Class
