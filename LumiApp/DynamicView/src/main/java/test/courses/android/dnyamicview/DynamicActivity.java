package test.courses.android.dnyamicview;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DynamicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        TextView text1 = new TextView(this);
        text1.setText(R.string.name);
        text1.setTextSize(20);
        text1.setId(R.id.tv1);

        ImageView image1 = new ImageView(this);
        image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_dominos));

// Code used for RelativeLayout
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        text1.setLayoutParams(params);

// For Constraint Layout we use the code below
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT);

    //    text1.setLayoutParams(params);
    //    image1.setLayoutParams(params);


        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.View1);
        constraintLayout.addView(text1);
        constraintLayout.addView(image1);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        //Below code adds constraints to textview widget. One constraint aligns the view to
        // the top side of the parent and second one make it align to right side of the parent with
        // constraint margin 10.

        constraintSet.connect(text1.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 50);
        constraintSet.connect(text1.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 10);

        // setting the location for the image using constraint
        constraintSet.connect(image1.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, 150);

        // to center in the middle
        constraintSet.connect(image1.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 10);
        constraintSet.connect(image1.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 10);
        constraintSet.setHorizontalBias(image1.getId(), 0.5f);


        constraintSet.applyTo(constraintLayout);



        // fix the orientation to portrait only
         setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



    }
}
