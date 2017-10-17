package arkstudios.lumiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ColorMenu extends AppCompatActivity {

    public ImageButton menu;
    public ImageButton settings;
    public ImageButton color;
    public ImageButton baby;
    public ImageButton notifications;
    public ImageButton timer;

    public Button test;

    public Button c1;//white
    public Button c2;//red
    public Button c3;//green
    public Button c4;//blue
    public Button c5;//light blue
    public Button c6;//pink
    public Button c7;//yellow

    public Button c8;//custom
    public Button c9;
    public Button c10;
    public Button c11;
    public Button c12;
    public Button c13;
    public Button c14;

    Intent colorChange;
    Intent nextScreen;
    Animation fadeIn;
    Animation fadeOut;

    public void init(){

        //Demo Button
        test = (Button) findViewById(R.id.test);
        //preset color
        c1 = (Button) findViewById(R.id.c1);
        c2 = (Button) findViewById(R.id.c2);
        c3 = (Button) findViewById(R.id.c3);
        c4 = (Button) findViewById(R.id.c4);
        c5 = (Button) findViewById(R.id.c5);
        c6 = (Button) findViewById(R.id.c6);
        c7 = (Button) findViewById(R.id.c7);
        //custom color
        c8 = (Button) findViewById(R.id.c8);
        c9 = (Button) findViewById(R.id.c9);
        c10 = (Button) findViewById(R.id.c10);
        c11 = (Button) findViewById(R.id.c11);
        c12 = (Button) findViewById(R.id.c12);
        c13 = (Button) findViewById(R.id.c13);
        c14 = (Button) findViewById(R.id.c14);

        //menu
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


    //menu Functions
    public void settingsButtonClicked(View view){
        nextScreen = new Intent(this, arkstudios.lumiapp.SettingsMenu.class);
        startActivity(nextScreen);
    }
    public void colorButtonClicked(View view){
        Toast.makeText(this, "You are already here!",
                Toast.LENGTH_SHORT).show();
    }
    public void babyButtonClicked(View view){
        nextScreen = new Intent(this, arkstudios.lumiapp.BabyMenu.class);
        startActivity(nextScreen);
    }
    public void notificationsButtonClicked(View view){
        nextScreen = new Intent(this, arkstudios.lumiapp.MessageBoardScreen.class);
        startActivity(nextScreen);
    }

    public void timerButtonClicked(View view){
        nextScreen = new Intent(this, arkstudios.lumiapp.TimerMenu.class);
        startActivity(nextScreen);
    }
    //color Functions

    public void onClick(View view)
    {
        //preset colors
        switch (view.getId()) {
            case R.id.c1:

                test.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.c2:
                test.setBackgroundColor(getResources().getColor(R.color.cRed));
                break;
            case R.id.c3:
                test.setBackgroundColor(getResources().getColor(R.color.cGreen));
                break;
            case R.id.c4:
                test.setBackgroundColor(getResources().getColor(R.color.cBlue));
                break;
            case R.id.c5:
                test.setBackgroundColor(getResources().getColor(R.color.cLightBlue));
                break;
            case R.id.c6:
                test.setBackgroundColor(getResources().getColor(R.color.cPink));
                break;
            case R.id.c7:
                test.setBackgroundColor(getResources().getColor(R.color.cYellow));
                break;

            //Custom colors
            case R.id.c8:
                test.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.c9:
                test.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.c10:
                test.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.c11:
                test.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.c12:
                test.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.c13:
                test.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.c14:
                test.setBackgroundColor(getResources().getColor(R.color.white));
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_menu);
        init();



        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.white));
                Toast.makeText(ColorMenu.this, R.string.selected_c1, Toast.LENGTH_SHORT).show();
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.cRed));
                Toast.makeText(ColorMenu.this, R.string.selected_c2, Toast.LENGTH_SHORT).show();
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.cGreen));
                Toast.makeText(ColorMenu.this, R.string.selected_c3, Toast.LENGTH_SHORT).show();
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.cBlue));
                Toast.makeText(ColorMenu.this, R.string.selected_c4, Toast.LENGTH_SHORT).show();
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.cLightBlue));
                Toast.makeText(ColorMenu.this, R.string.selected_c5, Toast.LENGTH_SHORT).show();
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.cPink));
                Toast.makeText(ColorMenu.this, R.string.selected_c6, Toast.LENGTH_SHORT).show();
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.cYellow));
                Toast.makeText(ColorMenu.this, R.string.selected_c7, Toast.LENGTH_SHORT).show();
            }
        });

        //Custom color
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.white));
                Toast.makeText(ColorMenu.this, R.string.selected_c8, Toast.LENGTH_SHORT).show();
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.white));
                Toast.makeText(ColorMenu.this, R.string.selected_c9, Toast.LENGTH_SHORT).show();
            }
        });
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.white));
                Toast.makeText(ColorMenu.this, R.string.selected_c10, Toast.LENGTH_SHORT).show();
            }
        });
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.white));
                Toast.makeText(ColorMenu.this, R.string.selected_c11, Toast.LENGTH_SHORT).show();
            }
        });
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.white));
                Toast.makeText(ColorMenu.this, R.string.selected_c12, Toast.LENGTH_SHORT).show();
            }
        });
        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.white));
                Toast.makeText(ColorMenu.this, R.string.selected_c13, Toast.LENGTH_SHORT).show();
            }
        });
        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setBackgroundColor(getResources().getColor(R.color.white));
                Toast.makeText(ColorMenu.this, R.string.selected_c14, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
