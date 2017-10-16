package arkstudios.lumiapp;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MessageBoardScreen extends AppCompatActivity {

    Typeface custom_font;
    TextView header;
    String messagetoSend, currentTime, user;
    EditText textfield;
    Button send;
    Date d;
    SimpleDateFormat sdf;
    ArrayList<String> chatList;
    ArrayAdapter<String> adapter;
    ListView list;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Set<String> set;


    public void setFont(){

        header.setTypeface(custom_font);
        textfield.setTypeface(custom_font);
        send.setTypeface(custom_font);



    }


    public void init(){

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Black.ttf");
        header = (TextView) findViewById(R.id.textView2);
        textfield = (EditText) findViewById(R.id.messageField);
        send = (Button) findViewById(R.id.sendButton);
//        d = new Date();
        sdf = new SimpleDateFormat("hh:mm a");
        list = (ListView) findViewById(R.id.message_List);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        set = new HashSet<String>();
        setFont();

        user = prefs.getString("logged_user", "no_id");
        chatList = new ArrayList<String>();
        if(prefs.getStringSet("messageLog", null) != null)
            chatList.addAll(prefs.getStringSet("messageLog", null));


    }

    public void addItems(){

        chatList.add(user +" :  " + messagetoSend + " [" +currentTime+ "]");
        set.addAll(chatList);
        editor.putStringSet("messageLog", set);
        editor.commit();
        adapter.notifyDataSetChanged();

    }
    public void sendMessage(View view){

        try{
            if(textfield.getText().toString().matches(""))
                return;
            else {
                messagetoSend = textfield.getText().toString();
                d = new Date();
                currentTime = sdf.format(d);
                addItems();
                textfield.setText("");

            }
        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board_screen);
        init();


        adapter = new ArrayAdapter<String>(this, R.layout.custom_list,R.id.list_content,chatList);
        list.setAdapter(adapter);

    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
