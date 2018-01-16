//TEAM NAME:    ARK STUDIOS
//PROJECT:      LUMI

package arkstudios.lumiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {

    Button nextButton, registerButton;
    TextView title;
    Intent nextScreen, registerScreen;
    EditText editTextName, editPassword;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Boolean nameOk, passwordOk, verifiedOK;
    Vibrator v;
    VibrationEffect vibe;
    Typeface custom_font;
    CheckBox remember_me;
    String  password;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference getpass;

    FirebaseDatabase database;
    DatabaseReference myRef;


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
        remember_me = (CheckBox) findViewById(R.id.checkBox);
        nameOk = false;
        passwordOk = false;
        verifiedOK = false;

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getInstance().getReference();
        user = auth.getCurrentUser();
        setFont();




        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();

        if(prefs.getString("Username", null) != null)
            editTextName.setText(prefs.getString("Username", null));

        remember_me.setChecked(prefs.getBoolean("username_isChecked", false));

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



    }// end init()


    public void goToRegisterScreen(View view){

        startActivity(registerScreen);

    }

    public void nextActivity(View view) {

        try {
            getpass = myRef.child(editTextName.getText().toString()).child("password");

        }catch(Exception e){}

        try{
        getpass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                password = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }catch(Exception e){
            Toast.makeText(this, getString(R.string.creds), Toast.LENGTH_LONG).show();
        }


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

            if(password.equals(editPassword.getText().toString()))
                verifiedOK = true;
            else {
                Toast.makeText(this, getString(R.string.creds), Toast.LENGTH_SHORT).show();
                verifiedOK = false;
            }
        }catch(Exception e){
        }


        if(testing(nameOk,passwordOk,verifiedOK)) {
            editor.putString("lumi_id", editTextName.getText().toString());
            editor.commit();
            startActivity(nextScreen);
        }
    }//end nextActivity()

    public boolean testing(boolean name, boolean pass, boolean verified){
        if(name && pass && verified)
            return true;
        else
            return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        init();

        remember_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b) {
                    editor.putString("Username", editTextName.getText().toString());
                    editor.putBoolean("username_isChecked", b);
                    editor.commit();
                }
                else{
                    editor.remove("Username");
                    editor.putBoolean("username_isChecked", b);
                    editor.commit();

                }
            }
        });

    }

}
