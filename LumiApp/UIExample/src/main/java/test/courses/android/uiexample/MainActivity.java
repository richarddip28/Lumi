package test.courses.android.uiexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioOptionGroup;
    private RadioButton radioOptionButton;
    private Button btnDisplay;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final CheckBox check_button = (CheckBox) findViewById(R.id.checkbox);
        check_button.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                TextView tv = (TextView)findViewById(R.id.checkbox);
                tv.setText(check_button.isChecked() ?
                        "This option is checked" :
                        "This option is not checked");
            }
        });

        addListenerOnButton();

        progress = (ProgressBar) findViewById(R.id.progressBar1);
        progress.setProgress(75);
    }

    public void addListenerOnButton() {

        radioOptionGroup = (RadioGroup) findViewById(R.id.RadioGroup01);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioOptionGroup.getCheckedRadioButtonId();

                // find the radio button by returned id
                radioOptionButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(MainActivity.this,
                        radioOptionButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

        radioOptionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("chk", "id" + checkedId);

                if (checkedId == R.id.RadioButton01) {
                    Toast.makeText(MainActivity.this,
                            "Radio Button 1 Checked!!", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.RadioButton02) {
                    Toast.makeText(MainActivity.this,
                            "Radio Button 2 Checked!!", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.RadioButton03) {
                    Toast.makeText(MainActivity.this,
                            "Radio Button 3 Checked!!", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

}
