package ARKstudios.lumiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

public class TimerMenu extends AppCompatActivity {

    public ImageButton menu;
    public ImageButton settings;
    public ImageButton color;
    public ImageButton baby;
    public ImageButton notifications;
    public ImageButton timer;
    Intent nextScreen;
    Animation fadeIn;
    Animation fadeOut;

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
        nextScreen = new Intent(this, SettingsMenu.class);
        startActivity(nextScreen);
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
        nextScreen = new Intent(this, NotificationsMenu.class);
        startActivity(nextScreen);
    }

    public void timerButtonClicked(View view){
        Toast.makeText(this, "You are already here!",
                Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_menu);
        init();
    }
}
