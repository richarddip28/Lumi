///*
//Name: Richard Dip
//Student Number: N00653804
//
// */

package comricharddip28.httpsgithub.richard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class TabFragment1 extends Fragment{

    TextView name_header;
    Button toggle_button, save_pref_button;
    Boolean toggle_state;
    SeekBar seekBar;
    int seekbarVal;
    SharedPreferences.Editor editor;
    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fragment_1, container, false);

        pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = pref.edit();

        toggle_state = false;
        seekbarVal = 0;

        name_header = v.findViewById(R.id.textView2);
        seekBar =  v.findViewById(R.id.seekBar);
        toggle_button =  v.findViewById(R.id.toggle_button);
        save_pref_button =  v.findViewById(R.id.pref_button);

        toggle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!toggle_state)
                {
                    toggle_button.setText("ON");
                    toggle_state = true;
                }
                else
                {
                    toggle_button.setText("OFF");
                    toggle_state = false;
                }
            }
        });

        save_pref_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekbarVal = seekBar.getProgress();
                editor.putString("name", name_header.getText().toString());
                editor.putBoolean("clicked", toggle_state);
                editor.putInt("seek", seekbarVal);
                editor.commit();
            }
        });



        return v;
    }
}
