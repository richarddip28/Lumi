//TEAM NAME:    ARK STUDIOS
//PROJECT:      LUMI

package arkstudios.lumiapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TabFragment2 extends Fragment{

    MediaPlayer mp;
    Spinner songList;
    ArrayAdapter adapter;
    List<String> list;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_fragment_2, container, false);
        songList = v.findViewById(R.id.spinner_song);
        list = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.song_list)));
        adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, list);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        songList.setAdapter(adapter);

        songList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    switch(i){

                        case 1:
                            if (mp != null) {
                                mp.release();
                                mp = null;
                            }
                            mp = MediaPlayer.create(getContext(), R.raw.rock_a_bye_baby);
                            mp.start();
                            break;
                        case 2:
                            if (mp != null) {
                                mp.release();
                                mp = null;
                            }
                            mp = MediaPlayer.create(getContext(), R.raw.twinkle_twinkle);
                            mp.start();
                            break;
                        default:
                            return;
                    }//end switch
                }//end onSelected

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        return v;

    }
}
