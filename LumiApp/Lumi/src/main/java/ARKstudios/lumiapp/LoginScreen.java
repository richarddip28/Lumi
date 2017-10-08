package ARKstudios.lumiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    Button nextButton, registerButton;
    TextView title;
    Intent nextScreen, registerScreen;
    EditText editTextName, editPassword;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Boolean nameOk, passwordOk;
    Vibrator v;
    VibrationEffect vibe;
    Typeface custom_font;

    public void setFont(){

        nextButton.setTypeface(custom_font);
        registerButton.setTypeface(custom_font);
        editTextName.setTypeface(custom_font);
        editPassword.setTypeface(custom_font);
        title.setTypeface(custom_font);

    }

    public void init(){

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Black.ttf");

        nextButton = (Button) findViewById(R.id.button4);
        registerButton = (Button) findViewById(R.id.button3);
        editTextName = (EditText) findViewById(R.id.editText);
        editPassword = (EditText) findViewById(R.id.editText2);
        title = (TextView) findViewById(R.id.textView);
        nextScreen = new Intent(this, MainMenuActivity.class);
        registerScreen = new Intent(this, RegisterScreen.class);


        setFont();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }// end init()

    public void getUserInfo(){

//        editor.putString("name_final", editTextName.getText().toString());
//        editor.putString("password_final", editPassword.getText().toString());

//        USER INFO STORED IN RegisterScreen
//        LOGIN SCREEN MUST MATCH INFO BEFORE CONTINUING

    }

    public void goToRegisterScreen(View view){

        startActivity(registerScreen);

    }

    public void nextActivity(View view){

        try{
            if(editTextName.getText().toString().matches("")){
                Toast.makeText(this, getString(R.string.name_error), Toast.LENGTH_SHORT).show();
                nameOk = false;
            }
            else
                nameOk = true;
            if(editPassword.getText().toString().matches("")){
                Toast.makeText(this, getString(R.string.password_error), Toast.LENGTH_SHORT).show();
                passwordOk = false;
            }
            else
                passwordOk = true;

        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


        if(nameOk && passwordOk) {
            getUserInfo();
            startActivity(nextScreen);
        }
    }//end nextActivity()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        init();


    }
}
