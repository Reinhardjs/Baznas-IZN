package com.karyastudio.izn.views.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.adapter.MyStepperAdapter;
import com.karyastudio.izn.model.api.datakdz.KajianDampakZakatResponse;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.fragments.FragmentModul1101;
import com.karyastudio.izn.views.fragments.FragmentModul1201;
import com.karyastudio.izn.views.fragments.FragmentModul1401;
import com.karyastudio.izn.views.fragments.FragmentModul1501;
import com.karyastudio.izn.views.fragments.FragmentModul1601;
import com.karyastudio.izn.views.fragments.FragmentModul1701;
import com.karyastudio.izn.views.fragments.FragmentModul1801;
import com.karyastudio.izn.views.fragments.FragmentModul1Likert1;
import com.karyastudio.izn.views.fragments.FragmentModul1Likert2;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SurveyKDZActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    private StepperLayout mStepperLayout;
    private ArrayList<Step> steps;
    public static String form_input_id;

    public static String form_request_type;
    public static final String FORM_REQUEST_TYPE_DATA = "FORM_REQUEST_TYPE_DATA";
    public static final String REQUEST_TYPE_INSERT = "INSERT";
    public static final String REQUEST_TYPE_UPDATE = "UPDATE";

    public static KajianDampakZakatResponse data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_kdz);

        Intent intent = getIntent();
        form_request_type = intent.getStringExtra(FORM_REQUEST_TYPE_DATA);
        data = (KajianDampakZakatResponse) intent.getSerializableExtra("data");


        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        form_input_id = formatter.format(date);
        Utils.log("FORM INPUT ID : " + form_input_id + " ("+SurveyKDZActivity.class.getSimpleName()+")");

        if (form_request_type.equals(REQUEST_TYPE_UPDATE)){
            form_input_id = data.getFk_id();
        }


        steps = new ArrayList<>();
        steps.add(new FragmentModul1101());
        steps.add(new FragmentModul1201());
        steps.add(new FragmentModul1401());
        steps.add(new FragmentModul1501());
        steps.add(new FragmentModul1601());
        steps.add(new FragmentModul1701());
        steps.add(new FragmentModul1801());
        steps.add(new FragmentModul1Likert1());
        steps.add(new FragmentModul1Likert2());

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new MyStepperAdapter(getSupportFragmentManager(), this, steps));

        mStepperLayout.setListener(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Toast.makeText(getApplicationContext(),"Configuration Changed", Toast.LENGTH_SHORT).show();

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCompleted(View completeButton) {
         // Toast.makeText(this, "onCompleted!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError(VerificationError verificationError) {
        // Toast.makeText(this, "onError! -> " + verificationError.getErrorMessage(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, verificationError.getErrorMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {
        // Toast.makeText(this, "onStepSelected! -> " + newStepPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReturn() {
        Toast.makeText(this, "on return", Toast.LENGTH_SHORT).show();
        finish();
    }
}
