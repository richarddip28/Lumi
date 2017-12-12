package arkstudios.lumiapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    ArrayAdapter<String> adapter;
    Spinner userList;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Boolean nextscreenOk;
    List<String> list;
    Context context;
    Set set;

    public void clearCache(View view){

        list = (Arrays.asList(getResources().getStringArray(R.array.user_list)));
        editor.clear();
        editor.commit();
        adapter.notifyDataSetChanged();
    }

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

        list = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.user_list)));
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        set = new HashSet<String>();


        userList = (Spinner) findViewById(R.id.spinner_users);

        context = getBaseContext();
        if(prefs.getStringSet("userList", null) != null) {
            list.clear();
            list.addAll(prefs.getStringSet("userList", null));
        }



        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        userList.setAdapter(adapter);
        userList.setSelection(prefs.getInt("start_pos", 0));




    }

        public void addUser(View view) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
            alertDialogBuilder.setTitle("Add User");
            final EditText usernamefield = new EditText(this);
            usernamefield.setBackgroundResource(R.drawable.edittext_style);
            alertDialogBuilder
                    .setView(usernamefield)
                    .setCancelable(false)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            if(usernamefield.getText().toString().matches(""))
                                Toast.makeText(context, getResources().getString(R.string.userfield_error), Toast.LENGTH_SHORT).show();
                            else if(usernamefield.getText().toString().length() < 3)
                                Toast.makeText(context, getResources().getString(R.string.userfield_short), Toast.LENGTH_SHORT).show();
                            else
                                list.add(usernamefield.getText().toString());
                                set.addAll(list);
                                editor.putStringSet("userList", set);
                                editor.commit();
                                adapter.notifyDataSetChanged();
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

    public void menuButtonClicked(View view){

        if (settings.getVisibility() == View.INVISIBLE) {


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
                            editor.putInt("start_pos", i);
                            editor.commit();
                            break;
                        case 2:
                            editor.putString("logged_user", userList.getSelectedItem().toString());
                            editor.commit();
                            Toast.makeText(getBaseContext(), getResources().getString(R.string.logged_toast)+userList.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            nextscreenOk=true;
                            editor.putInt("start_pos", i);
                            editor.commit();
                            break;
                        case 3:
                            editor.putString("logged_user", userList.getSelectedItem().toString());
                            editor.commit();
                            Toast.makeText(getBaseContext(), getResources().getString(R.string.logged_toast)+userList.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                            nextscreenOk=true;
                            editor.putInt("start_pos", i);
                            editor.commit();
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

        @Override
        public void onResume(){
            super.onResume();

        }
    }//end class