package com.karyastudio.izn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.managerSchema.KeluargaManager;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;
import com.pixplicity.easyprefs.library.Prefs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdapterKeluarga extends RecyclerView.Adapter<AdapterKeluarga.ViewHolder> implements Serializable {

    String m209 = "kosong";
    String m210 = "kosong";
    String m303 = "kosong";
    String m304 = "kosong";
    String m305 = "kosong";
    String m306 = "kosong";
    String m307 = "kosong";

    int count = 0;
    public Context context;
    List<Keluarga> list;

    public AdapterKeluarga(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<Keluarga> list){
        this.list = list;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @NonNull
    @Override
    public AdapterKeluarga.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data1_201, viewGroup, false);
        return new AdapterKeluarga.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterKeluarga.ViewHolder viewHolder, final int position) {

        if (list != null)
        if (list.size() > 0 && position < list.size()){

            viewHolder.edt_208.setPrompt(list.get(position).getFk_208());
            viewHolder.txt_209.setText(list.get(position).getFk_209());
            viewHolder.txt_210.setText(list.get(position).getFk_210());
            viewHolder.txt_304.setText(list.get(position).getFk_304());
            viewHolder.txt_305.setText(list.get(position).getFk_305());
            viewHolder.txt_306.setText(list.get(position).getFk_306());
            viewHolder.txt_307.setText(list.get(position).getFk_307());
            // viewHolder.edt_303.clearCheck()
            // viewHolder.edt_304.clearCheck()


            viewHolder.spn_305.setPrompt(list.get(position).getFk_305());
            viewHolder.spn_306.setPrompt(list.get(position).getFk_306());
            viewHolder.spn_307.setPrompt(list.get(position).getFk_307());

            viewHolder.edt_305.setText(list.get(position).getFk_305());
            viewHolder.edt_306.setText(list.get(position).getFk_306());
            viewHolder.edt_307.setText(list.get(position).getFk_307());

            viewHolder.edt_202nama.setText(list.get(position).getFk_202_nama());
            viewHolder.edt_202nik.setText(list.get(position).getFk_202_nik());
            viewHolder.edt_203.setPrompt(list.get(position).getFk_203());
            viewHolder.edt_204.setPrompt(list.get(position).getFk_204());
            viewHolder.edt_205.setText(list.get(position).getFk_205());
            viewHolder.edt_207.setPrompt(list.get(position).getFk_207());

            viewHolder.edt_209.setPrompt(list.get(position).getFk_209());

            //viewHolder.edt_210.clearCheck();

        }


        Prefs.putString(StaticStrings.M1_202nama, viewHolder.edt_202nama.getText().toString());
        Prefs.putString(StaticStrings.M1_202nik, viewHolder.edt_202nama.getText().toString());
        Prefs.putString(StaticStrings.M1_203, viewHolder.edt_203.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_204, viewHolder.edt_204.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_205, viewHolder.edt_205.getText().toString());
        Prefs.putString(StaticStrings.M1_207, viewHolder.edt_207.getSelectedItem().toString());
        Prefs.putString(StaticStrings.M1_208, viewHolder.edt_208.getSelectedItem().toString());

        if (viewHolder.edt_209.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_209, viewHolder.edt_209.getSelectedItem().toString());
            m209 = viewHolder.edt_209.getSelectedItem().toString();
        }
        if (viewHolder.edt_210.getVisibility() == View.VISIBLE) {
            int selectedId = viewHolder.edt_210.getCheckedRadioButtonId();
            RadioButton M1_210 = viewHolder.mView.findViewById(selectedId);
            Prefs.putString(StaticStrings.M1_210, M1_210.getText().toString());
            m210 = M1_210.getText().toString();
        }

            /*
                zona 3
             */
        int selectedId1 = viewHolder.edt_303.getCheckedRadioButtonId();
        RadioButton M1_303 = viewHolder.mView.findViewById(selectedId1);
        m303 = M1_303.getText().toString();
        Prefs.putString(StaticStrings.M1_303, M1_303.getText().toString());
        if (viewHolder.edt_304.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_304, viewHolder.edt_304.getSelectedItem().toString());
            m304 = viewHolder.edt_304.getSelectedItem().toString();
        }
        if (viewHolder.edt_305.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_305, viewHolder.edt_305.getText().toString());
            m305 = viewHolder.edt_305.getText().toString();
        }
        if (viewHolder.edt_306.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_306, viewHolder.edt_306.getText().toString());
            m306 = viewHolder.edt_306.getText().toString();
        }
        if (viewHolder.edt_307.getVisibility() == View.VISIBLE) {
            Prefs.putString(StaticStrings.M1_307, viewHolder.edt_307.getText().toString());
            m307 = viewHolder.edt_307.getText().toString();
        }

        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.edt_202nik.getText().toString().length() < 16) {
                    Toast.makeText(v.getContext(), "NIK harus 16 digit", Toast.LENGTH_LONG).show();
                } else if (viewHolder.edt_205.getText().toString().length() < 4){
                    Toast.makeText(v.getContext(), "Tahun lahir harus 4 digit", Toast.LENGTH_SHORT).show();
                } else if (viewHolder.edt_305.getText().toString().length() < 4 ||
                        viewHolder.edt_306.getText().toString().length() < 4 ||
                        viewHolder.edt_307.getText().toString().length() < 4) {
                    Toast.makeText(v.getContext(), "Nominal minimal harus 4 digit", Toast.LENGTH_LONG).show();
                }
                else {
                    int selectedId = viewHolder.edt_210.getCheckedRadioButtonId();
                    RadioButton M1_210 = viewHolder.mView.findViewById(selectedId);
                    Prefs.putString(StaticStrings.M1_210, M1_210.getText().toString());
                    int selectedId1 = viewHolder.edt_303.getCheckedRadioButtonId();
                    RadioButton M1_303 = viewHolder.mView.findViewById(selectedId1);
                    m210 = M1_210.getText().toString();
                    Toast.makeText(v.getContext(), "Data Dimasukan Antrian", Toast.LENGTH_SHORT).show();

                    Keluarga keluarga = new Keluarga();
                    keluarga.setFki_id(SurveyKDZActivity.form_input_id);
                    keluarga.setFki_fk_id(SurveyKDZActivity.form_input_id);
                    // keluarga.setFki_fk_id(Prefs.getString("parent_id", "kosong"));
                    keluarga.setFk_202_nama(viewHolder.edt_202nama.getText().toString());
                    keluarga.setFk_202_nik(viewHolder.edt_202nik.getText().toString());
                    keluarga.setFk_203(viewHolder.edt_203.getSelectedItem().toString());
                    keluarga.setFk_204(viewHolder.edt_204.getSelectedItem().toString());
                    keluarga.setFk_205(viewHolder.edt_205.getText().toString());
                    keluarga.setFk_206("");
                    keluarga.setFk_207(viewHolder.edt_207.getSelectedItem().toString());
                    keluarga.setFk_208(viewHolder.edt_208.getSelectedItem().toString());
                    keluarga.setFk_209(viewHolder.edt_209.getSelectedItem().toString());
                    keluarga.setFk_210(M1_210.getText().toString());
                    keluarga.setFk_303(M1_303.getText().toString());
                    keluarga.setFk_304(viewHolder.edt_304.getSelectedItem().toString());
                    keluarga.setFk_305(viewHolder.edt_305.getText().toString()+" "+viewHolder.spn_305.getSelectedItem().toString());
                    keluarga.setFk_306(viewHolder.edt_306.getText().toString()+" "+viewHolder.spn_306.getSelectedItem().toString());
                    keluarga.setFk_307(viewHolder.edt_307.getText().toString()+" "+viewHolder.spn_307.getSelectedItem().toString());
                    keluarga.setFk_308("");

                    list.add(keluarga);

//                    if ((KeluargaManager.loadAll(context).size() < 1)) {
                        // KeluargaManager.insertOrReplaceArray(context, list);

                    KeluargaManager.insertOrReplace(context, keluarga);

//                    } else {
//                        KeluargaManager.addNewList(KeluargaManager.loadAll(context), list, context);
//                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public Spinner edt_208;
        public TextView txt_209;
        public TextView txt_210;
        public TextView txt_304;
        public TextView txt_305;
        public TextView txt_306;
        public TextView txt_307;
        public RadioGroup edt_303;
        public Spinner edt_304;


        public Spinner spn_305;
        public Spinner spn_306;
        public Spinner spn_307;

        public EditText edt_305;
        public EditText edt_306;
        public EditText edt_307;

        public EditText edt_202nama;
        public EditText edt_202nik;
        public Spinner edt_203;
        public Spinner edt_204;
        public EditText edt_205;
        public Spinner edt_207;
        public Button btn;

        public Spinner edt_209;
        public RadioGroup edt_210;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            txt_209 = itemView.findViewById(R.id.txt1_209);
            txt_210 = itemView.findViewById(R.id.txt1_210);
            txt_304 = itemView.findViewById(R.id.txt1_304);
            txt_305 = itemView.findViewById(R.id.txt1_305);
            txt_306 = itemView.findViewById(R.id.txt1_306);
            txt_307 = itemView.findViewById(R.id.txt1_307);

            edt_208 = itemView.findViewById(R.id.edt1_208);
            edt_209 = itemView.findViewById(R.id.edt1_209);
            edt_210 = itemView.findViewById(R.id.edt1_210);


            edt_303 = itemView.findViewById(R.id.edt1_303);
            edt_304 = itemView.findViewById(R.id.edt1_304);


            spn_305 = itemView.findViewById(R.id.spin1_305);
            spn_306 = itemView.findViewById(R.id.spin1_306);
            spn_307 = itemView.findViewById(R.id.spin1_307);

            edt_305 = itemView.findViewById(R.id.edt1_305);
            edt_306 = itemView.findViewById(R.id.edt1_306);
            edt_307 = itemView.findViewById(R.id.edt1_307);

            edt_202nama = itemView.findViewById(R.id.edt1_202_nama);
            edt_202nik = itemView.findViewById(R.id.edt1_202_nik);
            edt_203 = itemView.findViewById(R.id.edt1_203);
            edt_204 = itemView.findViewById(R.id.edt1_204);
            edt_205 = itemView.findViewById(R.id.edt1_205);
            edt_207 = itemView.findViewById(R.id.edt1_207);
            btn = itemView.findViewById(R.id.check_btn);


            edt_208.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String temp = parent.getItemAtPosition(position).toString();
                    if (temp.equals("00. Belum/Tidak Bersekolah")) {
                        txt_209.setVisibility(View.GONE);
                        txt_210.setVisibility(View.GONE);
                        edt_209.setVisibility(View.GONE);
                        edt_210.setVisibility(View.GONE);
                    } else {
                        txt_209.setVisibility(View.VISIBLE);
                        txt_210.setVisibility(View.VISIBLE);
                        edt_209.setVisibility(View.VISIBLE);
                        edt_210.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            edt_303.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    switch (checkedId) {
                        case R.id.radioYa303:
                            txt_304.setVisibility(View.GONE);
                            txt_305.setVisibility(View.GONE);
                            txt_306.setVisibility(View.GONE);
                            txt_307.setVisibility(View.GONE);
                            edt_304.setVisibility(View.GONE);
                            edt_305.setVisibility(View.GONE);
                            edt_306.setVisibility(View.GONE);
                            edt_307.setVisibility(View.GONE);
                            spn_305.setVisibility(View.GONE);
                            spn_306.setVisibility(View.GONE);
                            spn_307.setVisibility(View.GONE);
                            break;
                        case R.id.radioTidak303:
                            txt_304.setVisibility(View.VISIBLE);
                            txt_305.setVisibility(View.VISIBLE);
                            txt_306.setVisibility(View.VISIBLE);
                            txt_307.setVisibility(View.VISIBLE);
                            edt_304.setVisibility(View.VISIBLE);
                            edt_305.setVisibility(View.VISIBLE);
                            edt_306.setVisibility(View.VISIBLE);
                            edt_307.setVisibility(View.VISIBLE);
                            spn_305.setVisibility(View.VISIBLE);
                            spn_306.setVisibility(View.VISIBLE);
                            spn_307.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            });


        }

    }
}
