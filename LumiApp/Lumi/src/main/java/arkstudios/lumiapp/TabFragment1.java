//TEAM NAME:    ARK STUDIOS
//PROJECT:      LUMI

package arkstudios.lumiapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Handler;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioRecord;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.VIBRATOR_SERVICE;
import static arkstudios.lumiapp.R.styleable.CompoundButton;

public class TabFragment1 extends Fragment {

    ProgressBar level;
    Handler handler;
    Switch s, record;
    SeekBar seekBar;
    VibrationEffect vibe;
    Vibrator vb;
    SensorManager sensorManager;
    Sensor sensor;
    TextView light;
    Date d;
    SimpleDateFormat sdf;
    String currentTime;
    TextView adjust_sens;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    ArrayList<String> low_light_triggered_log;
    Set<String> set;

        private void vibratePhone() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE)).vibrate(150);
        }
    }


    public void onResume() {
        super.onResume();
        sensorManager.registerListener(lightListener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(lightListener);
    }

    public SensorEventListener lightListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];

            light.setText("Current Light Reading: " + (int)x + " lux");
            d = new Date();
            currentTime = sdf.format(d);


            if((int)x<1 && s.isChecked()) {
                vibratePhone();
                d = new Date();
                currentTime = sdf.format(d);
                low_light_triggered_log.add("[" +currentTime+ "] Lumi Detected Low Light");
                set.addAll(low_light_triggered_log);

                editor.putStringSet("messageLog", set);
                editor.commit();



            }


        }
    };




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fragment_1, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        vb = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        s = (Switch) v.findViewById(R.id.switch1);
        seekBar = v.findViewById(R.id.seekBar);
        light = (TextView) v.findViewById(R.id.light);
        sdf = new SimpleDateFormat("hh:mm a");
        adjust_sens = v.findViewById(R.id.adjust_sens);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
        set = new HashSet<String>();
        low_light_triggered_log = new ArrayList<String>();

        if(prefs.getStringSet("messageLog", null) != null)
            set.addAll(prefs.getStringSet("messageLog", null));




        s.setChecked(prefs.getBoolean("light_toggle_state", false));
        seekBar.setProgress(prefs.getInt("baby_audio_sensitivity", 0));



        handler = new Handler();
        level = (ProgressBar) v.findViewById(R.id.progressBar);
        level.setMax(32676);
        record = (Switch) v.findViewById(R.id.switch2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    adjust_sens.setText("adjust sensitivity: " + i);
                    editor.putInt("baby_audio_sensitivity", i);
                    editor.commit();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if(s.isChecked()){
                        light.setVisibility(View.VISIBLE);
                        editor.putBoolean("light_toggle_state", b);
                        editor.commit();
                    }
                    else{
                        light.setVisibility(View.INVISIBLE);
                        editor.putBoolean("light_toggle_state", b);
                        editor.commit();
                    }

                }
            });



        //COMPLETE THIS CODE
        //SHOULD RECORD AUDIO AND CHECK FOR SOUND LEVELS
        record.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("crying_mode", b);
                editor.commit();
            }
        });

        return v;

    }
}
