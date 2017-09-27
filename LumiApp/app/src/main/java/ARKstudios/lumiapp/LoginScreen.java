package ARKstudios.lumiapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity {

    Button nextButton;
    Intent nextScreen;
    EditText editTextName, editPassword;

    public void init(){

        nextButton = (Button) findViewById(R.id.button);
        editTextName = (EditText) findViewById(R.id.editText);
        //editTextName.setBackgroundColor(Color.WHITE);
        editPassword = (EditText) findViewById(R.id.editText2);
        //editPassword.setBackgroundColor(Color.WHITE);

    }// end init()

    public void nextActivity(View view){

        nextScreen = new Intent(this, MainMenuActivity.class);
        startActivity(nextScreen);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        init();
    }
}
