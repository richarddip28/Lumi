package ARKstudios.lumiapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingsMenu extends AppCompatActivity {

    public ImageButton menu;
    public ImageButton settings;
    public ImageButton color;
    public ImageButton baby;
    public ImageButton notifications;
    public ImageButton timer;
    Intent nextScreen;
    Animation fadeIn;
    Animation fadeOut;

    protected Vibrator vibrate;


    public void init(){

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
        Toast.makeText(this, "You are already here!",
                Toast.LENGTH_LONG).show();
    }
    public void colorButtonClicked(View view){
        nextScreen = new Intent(this, ColorMenu.class);
        startActivity(nextScreen);
    }
    public void babyButtonClicked(View view){
        nextScreen = new Intent(this, BabyMenu.class);
        startActivity(nextScreen);
    }
    public void notificationsButtonClicked(View view){
        nextScreen = new Intent(this, MessageBoardScreen.class);
        startActivity(nextScreen);
    }

    public void timerButtonClicked(View view){
        nextScreen = new Intent(this, TimerMenu.class);
        startActivity(nextScreen);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_menu);
        init();

        vibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (vibrate == null) {
            Context context = getApplicationContext();
            Toast.makeText(context, "No Vibrator in this device!", Toast.LENGTH_SHORT);
        }


        Switch notifToggle = (Switch) findViewById(R.id.switch3);
        notifToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Context context = getApplicationContext();
                CharSequence notifTest = "Notifications are now on!";
                CharSequence notifTesttwo = "Notifications are off!";
                int duration = Toast.LENGTH_SHORT;

                if (isChecked) {
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    vibrate.vibrate(200);

                    Toast toast = Toast.makeText(context, notifTest, duration);
                    toast.show();
                }
                else {

                    vibrate.vibrate(200);

                    Toast toast = Toast.makeText(context, notifTesttwo, duration);
                    toast.show();
                }
            }
        });

//        Spinner spinner = (Spinner) findViewById(R.id.lang_spinner);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.lang_array, android.R.layout.simple_spinner_item);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(adapter);
    }
}
