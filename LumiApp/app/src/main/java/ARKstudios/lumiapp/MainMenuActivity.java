package ARKstudios.lumiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

        public ImageButton menu;
        public ImageButton settings;
        public ImageButton color;
        public ImageButton baby;
        public ImageButton notifications;
    

        public void init(){

            settings = (ImageButton)findViewById(R.id.imageButton7);
            color = (ImageButton)findViewById(R.id.imageButton6);
            baby = (ImageButton)findViewById(R.id.imageButton8);
            notifications = (ImageButton)findViewById(R.id.imageButton9);
            menu = (ImageButton) findViewById(R.id.imageButton5);


            try {
                menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        settings.setOnClickListener(this);

                        if(settings.getVisibility()==View.INVISIBLE) {

                            settings.setVisibility(View.VISIBLE);
                            color.setVisibility(View.VISIBLE);
                            baby.setVisibility(View.VISIBLE);
                            notifications.setVisibility(View.VISIBLE);

                            switch (view.getId()) {

                                case R.id.imageButton7:

                                    Toast.makeText(MainMenuActivity.this, "WORKING",
                                            Toast.LENGTH_LONG).show();
                                    Intent nextScreen = new Intent(MainMenuActivity.this, SettingsMenu.class);
                                    startActivity(nextScreen);
                                    break;

                                default:
                                    break;

                            }//end switch

                        }//end if

                        else{

                            settings.setVisibility(View.INVISIBLE);
                            color.setVisibility(View.INVISIBLE);
                            baby.setVisibility(View.INVISIBLE);
                            notifications.setVisibility(View.INVISIBLE);
                        }//end else


                    }//end onClick
                });
            } catch (Exception e) {

                Toast.makeText(MainMenuActivity.this, e.toString(),
                        Toast.LENGTH_LONG).show();
            }







        }//end init

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_menu);
            init();

        }
    }