package com.karyastudio.izn.views.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.karyastudio.izn.MainActivity;
import com.karyastudio.izn.R;
import com.karyastudio.izn.utils.StaticStrings;
import com.pixplicity.easyprefs.library.Prefs;

public class FragmentModul1601 extends Fragment {
    private FragmentManager fms;
    private Button btnNext;
    private Spinner edt_601;
    private Spinner edt_602;
    private TextView txt_603;
    private Spinner edt_603;
    private EditText edt_604;
    private TextView txt_604_lj;
    private EditText edt_604_lanjut;
    private EditText edt_605;
    private EditText edt_606;
    private Spinner edt_607;
    private TextView txt_608;
    private EditText edt_608;
    private TextView txt_609;
    private EditText edt_609;
    private TextView txt_610;
    private EditText edt_610;
    private TextView txt_611;
    private EditText edt_611;
    private TextView txt_612;
    private EditText edt_612;
    private TextView txt_613;
    private EditText edt_613;
    private TextView txt_614;
    private EditText edt_614;
    private TextView txt_615;
    private EditText edt_615;
    private TextView txt_616;
    private EditText edt_616;
    private TextView txt_617;
    private RadioGroup edt_617;
    private TextView txt_618;
    private EditText edt_618;


    public FragmentModul1601() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_modul1_601, container, false);
        fms = ((MainActivity) getActivity()).fms;
        Toolbar toolbar = rootView.findViewById(R.id.toolbar6);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);

        btnNext = rootView.findViewById(R.id.btn_next1_601);
        edt_601 = rootView.findViewById(R.id.edt1_601);
        edt_602 = rootView.findViewById(R.id.edt1_602);
        edt_603 = rootView.findViewById(R.id.edt1_603);
        edt_604 = rootView.findViewById(R.id.edt1_604);
        edt_604_lanjut = rootView.findViewById(R.id.edt1_604_lanjut);
        edt_605 = rootView.findViewById(R.id.edt1_605);
        edt_606 = rootView.findViewById(R.id.edt1_606);
        edt_607 = rootView.findViewById(R.id.edt1_607);
        edt_608 = rootView.findViewById(R.id.edt1_608);
        edt_609 = rootView.findViewById(R.id.edt1_609);
        edt_610 = rootView.findViewById(R.id.edt1_610);
        edt_611 = rootView.findViewById(R.id.edt1_611);
        edt_612 = rootView.findViewById(R.id.edt1_612);
        edt_613 = rootView.findViewById(R.id.edt1_613);
        edt_614 = rootView.findViewById(R.id.edt1_614);
        edt_615 = rootView.findViewById(R.id.edt1_615);
        edt_616 = rootView.findViewById(R.id.edt1_616);
        edt_617 = rootView.findViewById(R.id.edt1_617);
        edt_618 = rootView.findViewById(R.id.edt1_618);

        txt_603 = rootView.findViewById(R.id.txt1_603);
        txt_604_lj = rootView.findViewById(R.id.txt_604lj);
        txt_608 = rootView.findViewById(R.id.txt1_608);
        txt_609 = rootView.findViewById(R.id.txt1_609);
        txt_610 = rootView.findViewById(R.id.txt1_610);
        txt_611 = rootView.findViewById(R.id.txt1_611);
        txt_612 = rootView.findViewById(R.id.txt1_612);
        txt_613 = rootView.findViewById(R.id.txt1_613);
        txt_614 = rootView.findViewById(R.id.txt1_614);
        txt_615 = rootView.findViewById(R.id.txt1_615);
        txt_616 = rootView.findViewById(R.id.txt1_616);
        txt_617 = rootView.findViewById(R.id.txt1_617);
        txt_618 = rootView.findViewById(R.id.txt1_618);

        txt_616.setVisibility(View.GONE);
        edt_616.setVisibility(View.GONE);
        txt_612.setVisibility(View.GONE);
        edt_612.setVisibility(View.GONE);

        edt_602.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();
                if (temp.equals("1. BAZNAS Pusat")) {
                    txt_603.setVisibility(View.VISIBLE);
                    edt_603.setVisibility(View.VISIBLE);
                } else {
                    txt_603.setVisibility(View.GONE);
                    edt_603.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edt_607.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();

                if (temp.equals("1. Bantuan Konsumtif")) {
                    txt_608.setVisibility(View.VISIBLE);
                    edt_608.setVisibility(View.VISIBLE);
                    txt_609.setVisibility(View.VISIBLE);
                    edt_609.setVisibility(View.VISIBLE);
                    txt_610.setVisibility(View.VISIBLE);
                    edt_610.setVisibility(View.VISIBLE);
                    txt_611.setVisibility(View.VISIBLE);
                    edt_611.setVisibility(View.VISIBLE);
//                    txt_612.setVisibility(View.VISIBLE);
//                    edt_612.setVisibility(View.VISIBLE);

                    txt_613.setVisibility(View.GONE);
                    edt_613.setVisibility(View.GONE);
                    txt_614.setVisibility(View.GONE);
                    edt_614.setVisibility(View.GONE);
                    txt_615.setVisibility(View.GONE);
                    edt_615.setVisibility(View.GONE);

                    txt_617.setVisibility(View.GONE);
                    edt_617.setVisibility(View.GONE);
                    txt_618.setVisibility(View.GONE);
                    edt_618.setVisibility(View.GONE);

                } else if (temp.equals("2. Bantuan Produktif")) {
                    txt_608.setVisibility(View.GONE);
                    edt_608.setVisibility(View.GONE);
                    txt_609.setVisibility(View.GONE);
                    edt_609.setVisibility(View.GONE);
                    txt_610.setVisibility(View.GONE);
                    edt_610.setVisibility(View.GONE);
                    txt_611.setVisibility(View.GONE);
                    edt_611.setVisibility(View.GONE);
//                    txt_612.setVisibility(View.GONE);
//                    edt_612.setVisibility(View.GONE);

                    txt_613.setVisibility(View.VISIBLE);
                    edt_613.setVisibility(View.VISIBLE);
                    txt_614.setVisibility(View.VISIBLE);
                    edt_614.setVisibility(View.VISIBLE);
                    txt_615.setVisibility(View.VISIBLE);
                    edt_615.setVisibility(View.VISIBLE);
//                    txt_616.setVisibility(View.VISIBLE);
//                    edt_616.setVisibility(View.VISIBLE);
                    txt_617.setVisibility(View.VISIBLE);
                    edt_617.setVisibility(View.VISIBLE);
                    txt_618.setVisibility(View.VISIBLE);
                    edt_618.setVisibility(View.VISIBLE);
                } else {
                    txt_608.setVisibility(View.VISIBLE);
                    edt_608.setVisibility(View.VISIBLE);
                    txt_609.setVisibility(View.VISIBLE);
                    edt_609.setVisibility(View.VISIBLE);
                    txt_610.setVisibility(View.VISIBLE);
                    edt_610.setVisibility(View.VISIBLE);
                    txt_611.setVisibility(View.VISIBLE);
                    edt_611.setVisibility(View.VISIBLE);
//                    txt_612.setVisibility(View.VISIBLE);
//                    edt_612.setVisibility(View.VISIBLE);

                    txt_613.setVisibility(View.VISIBLE);
                    edt_613.setVisibility(View.VISIBLE);
                    txt_614.setVisibility(View.VISIBLE);
                    edt_614.setVisibility(View.VISIBLE);
                    txt_615.setVisibility(View.VISIBLE);
                    edt_615.setVisibility(View.VISIBLE);
//                    txt_616.setVisibility(View.VISIBLE);
//                    edt_616.setVisibility(View.VISIBLE);
                    txt_617.setVisibility(View.VISIBLE);
                    edt_617.setVisibility(View.VISIBLE);
                    txt_618.setVisibility(View.VISIBLE);
                    edt_618.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAndNext(rootView);
                fms.beginTransaction().replace(R.id.content_frames, new FragmentModul1701()).addToBackStack("7").commit();
            }
        });


        return rootView;
    }


    private void saveAndNext(View view) {
        Prefs.remove(StaticStrings.M1_601);
        Prefs.remove(StaticStrings.M1_602);
        Prefs.remove(StaticStrings.M1_603);
        Prefs.remove(StaticStrings.M1_604);
        Prefs.remove(StaticStrings.M1_605);
        Prefs.remove(StaticStrings.M1_606);
        Prefs.remove(StaticStrings.M1_607);
        Prefs.remove(StaticStrings.M1_608);
        Prefs.remove(StaticStrings.M1_609);
        Prefs.remove(StaticStrings.M1_610);
        Prefs.remove(StaticStrings.M1_611);
        Prefs.remove(StaticStrings.M1_612);
        Prefs.remove(StaticStrings.M1_613);
        Prefs.remove(StaticStrings.M1_614);
        Prefs.remove(StaticStrings.M1_615);
        Prefs.remove(StaticStrings.M1_616);
        Prefs.remove(StaticStrings.M1_617);
        Prefs.remove(StaticStrings.M1_618);

        Prefs.putString(StaticStrings.M1_601, edt_601.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_602, edt_602.getSelectedItem().toString());

        if (edt_603.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_603, edt_603.getSelectedItem().toString());
        }
        Prefs.putString(StaticStrings.M1_604, edt_604.getText() + "/" + edt_604_lanjut.getText().toString());

        Prefs.putString(StaticStrings.M1_605, edt_605.getText().toString());
        Prefs.putString(StaticStrings.M1_606, edt_606.getText().toString());
        Prefs.putString(StaticStrings.M1_607, edt_607.getSelectedItem().toString());

        if (edt_608.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_608, edt_608.getText().toString());
        }
        if (edt_609.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_609, edt_609.getText().toString());
        }
        if (edt_610.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_610, edt_610.getText().toString());
        }
        if (edt_611.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_611, edt_611.getText().toString());
        }
        if (edt_612.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_612, edt_612.getText().toString());
        }
        if (edt_613.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_613, edt_613.getText().toString());
        }
        if (edt_614.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_614, edt_614.getText().toString());
        }
        if (edt_615.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_615, edt_615.getText().toString());
        }
        if (edt_616.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_616, edt_616.getText().toString());
        }
        if (edt_617.getVisibility() == View.VISIBLE) {
            int selectedId = edt_617.getCheckedRadioButtonId();
            RadioButton M1_617 = view.findViewById(selectedId);
            Prefs.putString(StaticStrings.M1_617, M1_617.getText().toString());
        }
        if (edt_618.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_618, edt_618.getText().toString());
        }


    }
}