//TEAM NAME:    ARK STUDIOS
//PROJECT:      LUMI

package arkstudios.lumiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ColorMenu extends AppCompatActivity {

    public ImageButton menu;
    public ImageButton settings;
    public ImageButton color;
    public ImageButton baby;
    public ImageButton notifications;
    public ImageButton timer;
    Intent nextScreen;
    Animation fadeIn;
    Animation fadeOut;
    TextView description;

    ArrayAdapter<String> adapter;
    Spinner led_List;
    List<String> list;

    DatabaseReference myRef,getLED;
    FirebaseAuth auth;
    FirebaseUser user;

    int LED_option;

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

        description = findViewById(R.id.description);

        led_List = (Spinner) findViewById(R.id.spinner_led);
        list = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.led_list)));

        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        led_List.setAdapter(adapter);
        led_List.setSelection(0);

        try {
            auth = FirebaseAuth.getInstance();
            myRef = FirebaseDatabase.getInstance().getReference();
            user = auth.getCurrentUser();

        }catch(Exception e) {
        }

        getLED = myRef.child("LED");

        getLED.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LED_option = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        Toast.makeText(this, "You are already here!",
                Toast.LENGTH_LONG).show();
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

    public void setDescription(int option){
        switch(option){

            case 1: description.setText("Wipe color across display a pixel at a time");
                    break;

            case 2: description.setText("Movie theater light style chaser animation");
                    break;

            case 3: description.setText("Draw rainbow that fades across all pixels at once");
                    break;

            case 4: description.setText("Draw rainbow that uniformly distributes itself across all pixels");
                    break;

            case 5: description.setText("Rainbow movie theater light style chaser animation");
                    break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_menu);
        init();

        led_List.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                if(position==0)
                    led_List.setSelection(LED_option);
                else
                {
                    user = auth.getCurrentUser();
                    setDescription(position);
                    myRef.child("LED").setValue(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
