//TEAM NAME:    ARK STUDIOS
//PROJECT:      LUMI

package arkstudios.lumiapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MessageBoardScreen extends AppCompatActivity {

    Typeface custom_font;
    TextView header;
    String messagetoSend, currentTime, user, soundText, lightText;
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

    FirebaseAuth auth;
    FirebaseUser fUser;
    DatabaseReference getchatlist;
    DatabaseReference getCryingActivity, getLightActivity;
    FirebaseDatabase database;
    DatabaseReference myRef;
    InputMethodManager inputManager;

    public void setFont(){

        header.setTypeface(custom_font);
        textfield.setTypeface(custom_font);
        send.setTypeface(custom_font);



    }

    public static boolean isConnected(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
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
        inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        database = FirebaseDatabase.getInstance();
        myRef = database.getInstance().getReference();

        try {
            auth = FirebaseAuth.getInstance();
            getchatlist = FirebaseDatabase.getInstance().getReference().
                    child(prefs.getString("lumi_id", null)).child("chatlist");

            getCryingActivity = myRef.child("sound").child("Crying Activity");


            getCryingActivity.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    soundText = dataSnapshot.getValue(String.class);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            getLightActivity = myRef.child("brightness").child("Brightness Value");
            getLightActivity.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    lightText = dataSnapshot.getValue().toString();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            fUser = auth.getCurrentUser();

        }catch(Exception e) {
        }

        user = prefs.getString("logged_user", "no_id");
        chatList = new ArrayList<String>();

        if(!isConnected(this)) {
            if (prefs.getStringSet("messageLog", null) != null)
                chatList.addAll(prefs.getStringSet("messageLog", null));
        }
        else{

                getchatlist.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                             chatList.add(snapshot.getValue().toString());
                        }
                        getchatlist.removeEventListener(this);
                            chatList.add("Last Crying at " + soundText);
                            chatList.add("Brightness level: " + lightText);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        }
    }

    public void addItems(){

        chatList.add("[" +currentTime+ "] "+user + " :  " + messagetoSend);
        set.addAll(chatList);
        editor.putStringSet("messageLog", set);
        editor.commit();

//        adapter.notifyDataSetChanged();
        getchatlist.setValue(chatList);


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
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

            }
        }catch(Exception e){
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
