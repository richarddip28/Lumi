package arkstudios.lumiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity {

    Typeface custom_font;
    TextView reg_title,reg_header,lumi_sn,lumi_pass,lumi_pass_confirm;
    EditText SERIAL_NO, SERIAL_PASS, CON_PASS;
    Boolean serialOk, passOk;
    Intent nextScreen;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx = this;
    DatabaseOperations DB;
    String serial_no, serial_pass, con_pass;


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
        DB = new DatabaseOperations(ctx);

        setFont();

    }

    public void getUserInfo(){

//        editor.putString("lumi_serial_number", SERIAL_NO.getText().toString());
//        editor.putString("password_final", SERIAL_PASS.getText().toString());
        serial_no = SERIAL_NO.getText().toString();
        serial_pass = SERIAL_PASS.getText().toString();
        con_pass = CON_PASS.getText().toString();

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
        if(serialOk && passOk){

            try {
                getUserInfo();
                DB.putInformation(DB, serial_no, serial_pass);
                Toast.makeText(this, "Database successful", Toast.LENGTH_LONG).show();
                startActivity(nextScreen);
            }catch(Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        init();
    }
}
