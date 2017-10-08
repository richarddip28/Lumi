package ARKstudios.lumiapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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


    public void setFont(){

        header.setTypeface(custom_font);
        message.setTypeface(custom_font);
        textfield.setTypeface(custom_font);
        send.setTypeface(custom_font);



    }


    public void init(){

        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Lato-Black.ttf");
        header = (TextView) findViewById(R.id.textView2);
        message = (TextView) findViewById(R.id.messageBoard);
        message_board = (TextView) findViewById(R.id.messageBoard);
        textfield = (EditText) findViewById(R.id.messageField);
        send = (Button) findViewById(R.id.sendButton);
        d = new Date();
        sdf = new SimpleDateFormat("hh:mm a");


        setFont();

        //placeholder for future fetching
        user = "Richard";

    }

    public void sendMessage(View view){

        try{
            if(textfield.getText().toString().matches(""))
                return;
            else {
                messagetoSend = textfield.getText().toString();
                currentTime = sdf.format(d);
                message_board.setText(user +"  :  " + messagetoSend + " [" +currentTime+ "]");
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
    }
}
