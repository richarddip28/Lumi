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

public class MessageBoardScreen extends AppCompatActivity {

    Typeface custom_font;
    TextView header, message,message_board;
    EditText textfield;
    Button send;


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

        setFont();



    }

    public void sendMessage(View view){

        try{
            if(textfield.getText().toString().matches(""))
                return;
            else
                message_board.setText(textfield.getText().toString());

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
