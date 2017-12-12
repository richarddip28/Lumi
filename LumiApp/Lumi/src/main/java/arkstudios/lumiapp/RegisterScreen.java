//TEAM NAME:    ARK STUDIOS
//PROJECT:      LUMI

package arkstudios.lumiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterScreen extends AppCompatActivity {

    Typeface custom_font;
    TextView reg_title,reg_header,lumi_sn,lumi_pass,lumi_pass_confirm;
    EditText SERIAL_NO, SERIAL_PASS, CON_PASS;
    Boolean serialOk, passOk, serialnumberOK;
    Intent nextScreen;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseUser user;
    String[] serial_list = {    "489611038",
                                "062576422",
                                "759070491",
                                "906888478",
                                "251742905",
                                "157986134",
                                "494826689",
                                "054199001",
                                "973083557",
                                "993658539"
    };


    public void setFont(){

        reg_title.setTypeface(custom_font);
        reg_header.setTypeface(custom_font);
        lumi_sn.setTypeface(custom_font);
        lumi_pass.setTypeface(custom_font);
        lumi_pass_confirm.setTypeface(custom_font);

    }

    public void init(){

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Black.ttf");
        reg_title = (TextView) findViewById(R.id.reg_title);
        reg_header = (TextView) findViewById(R.id.reg_header);
        lumi_sn = (TextView) findViewById(R.id.lumi_sn);
        lumi_pass = (TextView) findViewById(R.id.lumi_pass);
        lumi_pass_confirm = (TextView) findViewById(R.id.lumi_pass_confirm);
        SERIAL_NO = (EditText) findViewById(R.id.editText6);
        SERIAL_PASS = (EditText) findViewById(R.id.editText7);
        CON_PASS = (EditText) findViewById(R.id.editText8);
        nextScreen = new Intent(this, MainMenuActivity.class);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        setFont();

        try {
            auth = FirebaseAuth.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference();
            user = auth.getCurrentUser();

        }catch(Exception e) {
        }


    }

    public void tryRegister(View view){

        try{
            if(SERIAL_NO.getText().toString().length() < 9){
                if(SERIAL_NO.getText().toString().matches(""))
                    Toast.makeText(this, getResources().getString(R.string.lumi_no_sn), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, getResources().getString(R.string.lumi_not_full), Toast.LENGTH_SHORT).show();

                serialOk = false;
            }
            else
                serialOk=true;

            for(int i=0;i<serial_list.length;i++){

                if(SERIAL_NO.getText().toString().equals(serial_list[i])) {
                    serialnumberOK = true;
                    i=serial_list.length + 1;
                }
                else
                    serialnumberOK = false;

            }

            if(!serialnumberOK)
                Toast.makeText(this, getString(R.string.invalid_lumi), Toast.LENGTH_SHORT).show();


            if (SERIAL_PASS.getText().toString().matches(CON_PASS.getText().toString())){
                if(SERIAL_PASS.getText().toString().matches("")) {
                    Toast.makeText(this, getResources().getString(R.string.pass_empty), Toast.LENGTH_SHORT).show();
                    passOk = false;
                }
                else
                    passOk = true;
            }
            else {
                Toast.makeText(this, getResources().getString(R.string.pass_not_match), Toast.LENGTH_SHORT).show();
                passOk = false;
            }

        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        if(serialOk && passOk && serialnumberOK){

            try {
                registerUser();
                editor.putString("lumi_id", SERIAL_NO.getText().toString());
                editor.commit();
                startActivity(nextScreen);
            }catch(Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }

    }

    public void registerUser(){

        String serial = SERIAL_NO.getText().toString();
        String sPass = SERIAL_PASS.getText().toString();
        String id = databaseReference.push().getKey();

        UserInformation userInformation = new UserInformation(sPass);
        user = auth.getCurrentUser();
        databaseReference.child(serial).setValue(userInformation);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        init();

    }
}
