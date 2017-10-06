package ARKstudios.lumiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
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

    Button nextButton;
    Intent nextScreen;
    EditText editTextName, editPassword;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Boolean nameOk, passwordOk;
    Vibrator v;
    VibrationEffect vibe;

    public void init(){

        nextButton = (Button) findViewById(R.id.button);
        editTextName = (EditText) findViewById(R.id.editText);
        editPassword = (EditText) findViewById(R.id.editText2);
        nextScreen = new Intent(this, MainMenuActivity.class);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


    }// end init()

    public void getUserInfo(){

        editor.putString("name_final", editTextName.getText().toString());
        editor.putString("password_final", editPassword.getText().toString());

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
