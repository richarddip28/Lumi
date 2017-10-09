package ARKstudios.lumiapp;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.app.ListActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MessageBoardScreen extends AppCompatActivity {

    Typeface custom_font;
    TextView header, message,message_board;
    String messagetoSend, currentTime, user;
    EditText textfield;
    Button send;
    Date d;
    SimpleDateFormat sdf;
    ArrayList<String> chatList;
    ArrayAdapter<String> adapter;
    ListView list;
    SharedPreferences prefs;


    public void setFont(){

        header.setTypeface(custom_font);
//        message.setTypeface(custom_font);
        textfield.setTypeface(custom_font);
        send.setTypeface(custom_font);



    }


    public void init(){

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Black.ttf");
        header = (TextView) findViewById(R.id.textView2);
//        message = (TextView) findViewById(R.id.messageBoard);
//        message_board = (TextView) findViewById(R.id.messageBoard);
        textfield = (EditText) findViewById(R.id.messageField);
        send = (Button) findViewById(R.id.sendButton);
        d = new Date();
        sdf = new SimpleDateFormat("hh:mm a");
        chatList = new ArrayList<String>();
        list = (ListView) findViewById(R.id.message_List);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        setFont();

        user = prefs.getString("logged_user", "no_id");

    }

    public void addItems(){

        chatList.add(user +"  :  " + messagetoSend + " [" +currentTime+ "]");
        adapter.notifyDataSetChanged();

    }
    public void sendMessage(View view){

        try{
            if(textfield.getText().toString().matches(""))
                return;
            else {
                messagetoSend = textfield.getText().toString();
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
}
