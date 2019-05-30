package com.karyastudio.izn.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.ArrayList;

public class MyStepperAdapter extends AbstractFragmentStepAdapter {

    ArrayList<Step> steps;

    public MyStepperAdapter(FragmentManager fm, Context context, ArrayList<Step> steps) {
        super(fm, context);
        this.steps = steps;
    }

    @Override
    public Step createStep(int position) {
        Log.d("MYAPP", "POSITION : " + position);
        return steps.get(position);

//        final StepFragmentSample step = new StepFragmentSample();
//        Bundle b = new Bundle();
//        b.putInt(CURRENT_STEP_POSITION_KEY, position);
//        step.setArguments(b);
//        return step;
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        return new StepViewModel.Builder(context)
                .setTitle("Survey Kajian Dampak Zakat") //can be a CharSequence instead
                .create();
    }
}
