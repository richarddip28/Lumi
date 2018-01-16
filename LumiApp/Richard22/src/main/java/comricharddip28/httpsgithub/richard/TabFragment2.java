///*
//Name: Richard Dip
//Student Number: N00653804
//
// */

package comricharddip28.httpsgithub.richard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TabFragment2 extends Fragment {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    TextView Name, SeekVal, Clicked;
    Button fetch_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fragment_2, container, false);

        prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = prefs.edit();


        Name = v.findViewById(R.id.textView3);
        SeekVal = v.findViewById(R.id.textView4);
        Clicked = v.findViewById(R.id.textView5);
        fetch_button = v.findViewById(R.id.fetch_pref_button);

        fetch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name.setText("Name is: " + prefs.getString("name", "no name"));
                Clicked.setText("Toggle is: "+ prefs.getBoolean("clicked", false));
                SeekVal.setText("SeekBar position is: " + prefs.getInt("seek", 0));
            }
        });

        return v;
    }
}
