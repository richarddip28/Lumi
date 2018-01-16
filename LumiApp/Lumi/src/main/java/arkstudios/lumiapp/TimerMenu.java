//TEAM NAME:    ARK STUDIOS
//PROJECT:      LUMI

package arkstudios.lumiapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TimerMenu extends AppCompatActivity implements OnClickListener {

    public ImageButton menu;
    public ImageButton settings;
    public ImageButton color;
    public ImageButton baby;
    public ImageButton notifications;
    public ImageButton timer;
    Intent nextScreen;
    Animation fadeIn;
    Animation fadeOut;

    Context context;

    TextView timerTextView;
    EditText minutes;
    Button setButton;
    Button startButton;
    Button addButton;
    CountDownTimer countDownTimer;

    ListView list;
    ArrayList arrayList;

    ArrayAdapter adapter;


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

        timerTextView =(TextView)findViewById(R.id.timerTextView);
        setButton = (Button)findViewById(R.id.setButton);
        startButton = (Button)findViewById(R.id.startButton);
        addButton = (Button)findViewById(R.id.addButton);


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
        nextScreen = new Intent(this, MessageBoardScreen.class);
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


        timerTextView = (TextView) findViewById(R.id.timerTextView);
        minutes = (EditText) findViewById(R.id.editCustomTime);
        startButton = (Button) findViewById(R.id.startButton);
        setButton = (Button) findViewById(R.id.setButton);

        setListeners();

        list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        list.setAdapter(adapter);

        addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TimerMenu.this);
                alertDialogBuilder.setTitle("Add New Timer");
                final EditText newCustomTimer = new EditText(TimerMenu.this);
                newCustomTimer.setBackgroundResource(R.drawable.edittext_style);
                alertDialogBuilder
                        .setView(newCustomTimer)
                        .setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                arrayList.add(newCustomTimer.getText().toString());
                                dialog.cancel();
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }


    //Set Listeners over button
    private void setListeners() {
        startButton.setOnClickListener(this);
        setButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton:
                //If CountDownTimer is null then start timer
                if (countDownTimer == null) {
                    String getMinutes = minutes.getText().toString();//Get minutes from edittext
                    //Check validation over edittext
                    if (!getMinutes.equals("") && getMinutes.length() > 0) {
                        int noOfMinutes = Integer.parseInt(getMinutes) * 60 * 1000;//Convert minutes into milliseconds

                        startTimer(noOfMinutes);//start countdown
                        startButton.setText(getString(R.string.stop_timer));//Change Text
                        setButton.setText(getString(R.string.reset_timer));

                    } else
                        Toast.makeText(TimerMenu.this, "Please enter no. of Minutes.", Toast.LENGTH_SHORT).show();//Display toast if edittext is empty
                } else {
                    //Else stop timer and change text
                    stopCountdown();
                    String getMinutes = minutes.getText().toString();
                    timerTextView.setText(getMinutes);
                    startButton.setText(getString(R.string.resumeButton));
                }
                break;

            case R.id.setButton:
                /*String getMinutes = minutes.getText().toString();
                if (!getMinutes.equals("") && getMinutes.length() > 0) {
                    long millisUntilFinished;
                    long millis = millisUntilFinished;
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    timerTextView.setText(hms);
                }*/
                stopCountdown();//stop count down
                startButton.setText(getString(R.string.startButton));//Change text to Start Timer7
                setButton.setText(getString(R.string.setButton));
                timerTextView.setText(getString(R.string.timerDefault));//Change Timer text
                break;
        }


    }


    //Stop Countdown method
    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    //Start Countdown method
    private void startTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                timerTextView.setText(hms);//set text
            }

            public void onFinish() {

                //On finish change timer text
                countDownTimer = null;//set CountDownTimer to null
                startButton.setText(getString(R.string.startButton));//Change button text


                /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                AlertDialog alertDialog = alertDialogBuilder.create();
                // set title
                //alertDialogBuilder.setTitle("Timer Over.");
                // set dialog message
                alertDialogBuilder
                        .setMessage("Check Baby food")
                        .setCancelable(false)
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog


                // show it
                alertDialog.show();*/
            }
        }.start();

    }

}
