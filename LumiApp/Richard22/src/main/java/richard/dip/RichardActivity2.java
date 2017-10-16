//  Richard Dip
//  N00653804

package richard.dip;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RichardActivity2 extends AppCompatActivity {

    Button button;
    EditText edittext;
    TextView textview;

    public void init(){

        button = (Button) findViewById(R.id.button);
        edittext = (EditText) findViewById(R.id.editText3);
        textview = (TextView) findViewById(R.id.textView5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case R.id.info_click:
                Toast.makeText(this, "Richard Dip: N00653804",Toast.LENGTH_LONG).show();

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        alertDialogBuilder.setTitle("QUIT?");
        alertDialogBuilder.setMessage("Are you sure you want to exit the app?");
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richard2);
        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textview.setText(edittext.getText().toString());
                edittext.setText("");

            }
        });


    }
}
