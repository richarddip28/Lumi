package ARKstudios.lumiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    public ImageButton menu;
    public ImageButton settings;
    public ImageButton color;
    public ImageButton baby;
    public ImageButton notifications;
    public ImageButton timer;
    Typeface custom_font;
    TextView tv;
    Intent nextScreen;
    Animation fadeIn;
    Animation fadeOut;
    ArrayAdapter adapter;
    Spinner userList;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Boolean nextscreenOk;

    public void init(){

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Black.ttf");
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
        tv = (TextView) findViewById(R.id.textView);
        tv.setTypeface(custom_font);
        userList = (Spinner) findViewById(R.id.spinner_users);
        adapter = ArrayAdapter.createFromResource(this, R.array.user_list, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        userList.setAdapter(adapter);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();




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
        if(nextscreenOk)
            startActivity(nextScreen);
        else
            Toast.makeText(this, getResources().getString(R.string.need_user), Toast.LENGTH_SHORT).show();
    }
    public void colorButtonClicked(View view){
        nextScreen = new Intent(this, ColorMenu.class);
        if(nextscreenOk)
            startActivity(nextScreen);
        else
            Toast.makeText(this, getResources().getString(R.string.need_user), Toast.LENGTH_SHORT).show();
    }
    public void babyButtonClicked(View view){
        nextScreen = new Intent(this, BabyMenu.class);
        if(nextscreenOk)
            startActivity(nextScreen);
        else
            Toast.makeText(this, getResources().getString(R.string.need_user), Toast.LENGTH_SHORT).show();
    }
    public void notificationsButtonClicked(View view){
        nextScreen = new Intent(this, MessageBoardScreen.class);
        if(nextscreenOk)
            startActivity(nextScreen);
        else
            Toast.makeText(this, getResources().getString(R.string.need_user), Toast.LENGTH_SHORT).show();
    }

    public void timerButtonClicked(View view){
        nextScreen = new Intent(this, TimerMenu.class);
        if(nextscreenOk)
            startActivity(nextScreen);
        else
            Toast.makeText(this, getResources().getString(R.string.need_user), Toast.LENGTH_SHORT).show();
    }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_menu);
            init();

            userList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    switch(i){

                        case 1:
                            editor.putString("logged_user", userList.getSelectedItem().toString());
                            editor.commit();
                            Toast.makeText(getBaseContext(), getResources().getString(R.string.logged_toast)+userList.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            nextscreenOk=true;
                            break;
                        case 2:
                            editor.putString("logged_user", userList.getSelectedItem().toString());
                            editor.commit();
                            Toast.makeText(getBaseContext(), getResources().getString(R.string.logged_toast)+userList.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            nextscreenOk=true;
                            break;
                        default:
                            nextscreenOk=false;
                            break;

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

        }//end onCreate
    }//end class