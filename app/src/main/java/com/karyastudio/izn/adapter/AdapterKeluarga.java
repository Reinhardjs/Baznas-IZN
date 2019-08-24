package com.karyastudio.izn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.Keluarga;
import com.karyastudio.izn.dao.managerSchema.KeluargaManager;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdapterKeluarga extends RecyclerView.Adapter<AdapterKeluarga.ViewHolder> implements Serializable {

    int count = 0;
    public Context context;
    private SparseArray<Keluarga> list = new SparseArray<>();

    public AdapterKeluarga(Context context) {
        this.context = context;
    }

    public void setList(SparseArray<Keluarga> list){
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

        // Gunakan position untuk layout recyclerview yang fix (tidak tambah atau berkurang) di tampilan
        // Gunakan viewHolder.getAdapterPosition untuk data bind untuk future remove/add
        // Gunakan viewHolder.getLayoutPosition jika data yang sedang dihapus membutuhkan posisi lamanya.

        if (list != null && list.get(position) != null) {
            viewHolder.edt_202nik.setText(list.get(position).getFk_202_nik());
            viewHolder.edt_202nama.setText(list.get(position).getFk_202_nama());


            String m1_203 = list.get(position).getFk_203();
            if (!m1_203.isEmpty()) {
                List<String> list203 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_203)));
                // viewHolder.edt_203.setSelection(list203.indexOf(m1_203));
                viewHolder.edt_203.setSelection(list203.indexOf(m1_203));
            }

            String m1_204 = list.get(position).getFk_204();
            if (!m1_204.isEmpty()) {
                List<String> list204 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_204)));
                viewHolder.edt_204.setSelection(list204.indexOf(m1_204));
            }

            viewHolder.edt_205.setText(list.get(position).getFk_205());

            String m1_207 = list.get(position).getFk_207();
            if (!m1_207.isEmpty()) {
                List<String> list207 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_207)));
                viewHolder.edt_207.setSelection(list207.indexOf(m1_207));
            }

            String m1_208 = list.get(position).getFk_208();
            if (!m1_208.isEmpty()) {
                List<String> list208 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_208)));
                viewHolder.edt_208.setSelection(list208.indexOf(m1_208));
            }

            String m1_209 = list.get(position).getFk_209();
            if (!m1_209.isEmpty()) {
                List<String> list209 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_209)));
                viewHolder.edt_209.setSelection(list209.indexOf(m1_209));
            }

            viewHolder.edt_210.clearCheck();
            String m1_210 = list.get(position).getFk_210();
            if (m1_210.equals("1. Ya (Jika Memiliki Sertifikat)")) {
                ((RadioButton) viewHolder.mView.findViewById(R.id.radioYa210)).setChecked(true);
            } else {
                ((RadioButton) viewHolder.mView.findViewById(R.id.radioTidak210)).setChecked(true);
            }

            viewHolder.edt_303.clearCheck();
            String m1_303 = list.get(position).getFk_303();
            if (m1_303.equals("0. Tidak Bekerja")) {
                ((RadioButton) viewHolder.mView.findViewById(R.id.radioYa303)).setChecked(true);
            } else {
                ((RadioButton) viewHolder.mView.findViewById(R.id.radioTidak303)).setChecked(true);
            }

            // Setiap ada pilihan radio button, yang jenisnya dua pilihan.
            // Selalu check bahwa nilainya tidak sama dengan "kosong"
            // kalau "kosong" nanti selalu kena ke logika else nya.
            String m1_304 = list.get(position).getFk_304();
            if (!m1_304.isEmpty() && !m1_304.equals("kosong")) {
                List<String> list304 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_304)));
                viewHolder.edt_304.setSelection(list304.indexOf(m1_304));
            }

            String m1_305 = "per " + list.get(position).getFk_305().split(" ")[2];
            if (!m1_305.isEmpty() && !m1_305.equals("kosong")) {
                List<String> list305 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_per)));
                viewHolder.spn_305.setSelection(list305.indexOf(m1_305));
            }
            viewHolder.edt_305.setText(list.get(position).getFk_305().split(" ")[0]);

            String m1_306 = "per " + list.get(position).getFk_306().split(" ")[2];
            if (!m1_306.isEmpty() && !m1_306.equals("kosong")) {
                List<String> list306 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_per)));
                viewHolder.spn_306.setSelection(list306.indexOf(m1_306));
            }
            viewHolder.edt_306.setText(list.get(position).getFk_306().split(" ")[0]);

            String m1_307 = "per " + list.get(position).getFk_307().split(" ")[2];
            if (!m1_307.isEmpty() && !m1_307.equals("kosong")) {
                List<String> list307 = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.items_per)));
                viewHolder.spn_307.setSelection(list307.indexOf(m1_307));
            }
            viewHolder.edt_307.setText(list.get(position).getFk_307().split(" ")[0]);

        }


        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.edt_202nik.getText().toString().length() < 16) {
                    Toast.makeText(v.getContext(), "NIK harus 16 digit", Toast.LENGTH_LONG).show();
                    return;
                } else if (viewHolder.edt_205.getText().toString().length() < 4){
                    Toast.makeText(v.getContext(), "Tahun lahir harus 4 digit", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (viewHolder.bekerjaForm.getVisibility() == View.VISIBLE) {
                    if (viewHolder.edt_305.getText().toString().length() < 4 ||
                            viewHolder.edt_306.getText().toString().length() < 4 ||
                            viewHolder.edt_307.getText().toString().length() < 4) {
                        Toast.makeText(v.getContext(), "Nominal minimal harus 4 digit", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                try {


                    RadioButton M1_210 = null;

                    if (viewHolder.edt_210.getVisibility() == View.VISIBLE){
                        int selectedId = viewHolder.edt_210.getCheckedRadioButtonId();
                        M1_210 = viewHolder.mView.findViewById(selectedId);
                    }


                    int selectedId1 = viewHolder.edt_303.getCheckedRadioButtonId();
                    RadioButton M1_303 = viewHolder.mView.findViewById(selectedId1);
                    // Prefs.putString(StaticStrings.M1_303, M1_303.getText().toString());

                    // checking for unique nik
                    int tempPos = -1;
                    for (Keluarga keluarga : KeluargaManager.loadAllwithFKID(context, SurveyKDZActivity.form_input_id)){
                        ++tempPos;

                        if (tempPos == position)
                            continue;
                        if (keluarga.getFk_202_nik().equals(viewHolder.edt_202nik.getText().toString())){
                            throw new Exception("NIK harus unik, tidak boleh ada yang sama.");
                        }
                    }


                    Keluarga keluarga = null;

                    if (list.get(position) == null){
                        keluarga = new Keluarga();
                        list.put(position, keluarga);

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                        Date date = new Date();
                        String fki_id = position + "" + position + "" + position + "" + position + "GREENDAO-KDZ" + formatter.format(date);
                        keluarga.setFki_id(fki_id);
                        keluarga.setFki_fk_id(SurveyKDZActivity.form_input_id);
                    } else {
                        keluarga = list.get(position);
                    }

                    keluarga.setPosisi(position);
                    keluarga.setStatus(StaticStrings.KDZ_STATUS_PENDING);
                    keluarga.setRequest_type(SurveyKDZActivity.form_request_type);
                    keluarga.setFk_202_nama(viewHolder.edt_202nama.getText().toString());
                    keluarga.setFk_202_nik(viewHolder.edt_202nik.getText().toString());
                    keluarga.setFk_203(viewHolder.edt_203.getSelectedItem().toString());
                    keluarga.setFk_204(viewHolder.edt_204.getSelectedItem().toString());
                    keluarga.setFk_205(viewHolder.edt_205.getText().toString());
                    keluarga.setFk_206("");
                    keluarga.setFk_207(viewHolder.edt_207.getSelectedItem().toString());
                    keluarga.setFk_208(viewHolder.edt_208.getVisibility() != View.VISIBLE ? "-1. Nochoice" : viewHolder.edt_208.getSelectedItem().toString());
                    keluarga.setFk_209(viewHolder.edt_209.getVisibility() != View.VISIBLE ? "-1. Nochoice" : viewHolder.edt_209.getSelectedItem().toString());

                    if (viewHolder.edt_210.getVisibility() == View.GONE) {
                        keluarga.setFk_210("");
                    } else {
                        if (M1_210 == null)
                            throw  new Exception("Pilihan kepemilikan sertifikat pendidikan nonformal harus dipilih");
                        keluarga.setFk_210(M1_210.getText().toString());
                    }

                    keluarga.setFk_303(M1_303.getText().toString());
                    keluarga.setFk_304(viewHolder.edt_304.getVisibility() != View.VISIBLE ? "-1. Nochoice" : viewHolder.edt_304.getSelectedItem().toString());
                    keluarga.setFk_305((viewHolder.edt_305.getVisibility() != View.VISIBLE ? "0" : viewHolder.edt_305.getText().toString()) + " " + viewHolder.spn_305.getSelectedItem().toString());
                    keluarga.setFk_306((viewHolder.edt_306.getVisibility() != View.VISIBLE ? "0" : viewHolder.edt_306.getText().toString()) + " " + viewHolder.spn_306.getSelectedItem().toString());
                    keluarga.setFk_307((viewHolder.edt_307.getVisibility() != View.VISIBLE ? "0" : viewHolder.edt_307.getText().toString()) + " " + viewHolder.spn_307.getSelectedItem().toString());
                    keluarga.setFk_308("");

                    Utils.toast(context, "Data personal berhasil disimpan");

                } catch (Exception e){
                    Utils.log("EXCEPTION e : " + e.getMessage() + " | " + e.toString());
                    if (e.getMessage() != null){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Mohon lengkapi form personal", Toast.LENGTH_SHORT).show();
                    }
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

        public LinearLayout bekerjaForm;
        public LinearLayout pendidikanForm;

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

            bekerjaForm = itemView.findViewById(R.id.bekerjaForm);
            pendidikanForm = itemView.findViewById(R.id.pendidikanForm);

            btn = itemView.findViewById(R.id.check_btn);

            edt_208.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String temp = parent.getItemAtPosition(position).toString();
                    if (temp.equals("00. Belum Bersekolah")) {
                        pendidikanForm.setVisibility(View.GONE);
                        edt_210.setVisibility(View.GONE);
                        txt_210.setVisibility(View.GONE);
                    } else if (temp.equals("01. Tidak Bersekolah")) {
                        pendidikanForm.setVisibility(View.GONE);
                        edt_210.setVisibility(View.VISIBLE);
                        txt_210.setVisibility(View.VISIBLE);
                    } else {
                        pendidikanForm.setVisibility(View.VISIBLE);
                        edt_210.setVisibility(View.VISIBLE);
                        txt_210.setVisibility(View.VISIBLE);
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
//                            txt_304.setVisibility(View.GONE);
//                            txt_305.setVisibility(View.GONE);
//                            txt_306.setVisibility(View.GONE);
//                            txt_307.setVisibility(View.GONE);
//                            edt_304.setVisibility(View.GONE);
//                            edt_305.setVisibility(View.GONE);
//                            edt_306.setVisibility(View.GONE);
//                            edt_307.setVisibility(View.GONE);
//                            spn_305.setVisibility(View.GONE);
//                            spn_306.setVisibility(View.GONE);
//                            spn_307.setVisibility(View.GONE);
                            bekerjaForm.setVisibility(View.GONE);
                            break;
                        case R.id.radioTidak303:
//                            txt_304.setVisibility(View.VISIBLE);
//                            txt_305.setVisibility(View.VISIBLE);
//                            txt_306.setVisibility(View.VISIBLE);
//                            txt_307.setVisibility(View.VISIBLE);
//                            edt_304.setVisibility(View.VISIBLE);
//                            edt_305.setVisibility(View.VISIBLE);
//                            edt_306.setVisibility(View.VISIBLE);
//                            edt_307.setVisibility(View.VISIBLE);
//                            spn_305.setVisibility(View.VISIBLE);
//                            spn_306.setVisibility(View.VISIBLE);
//                            spn_307.setVisibility(View.VISIBLE);
                            bekerjaForm.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            });

            edt_303.clearCheck();
            ((RadioButton)edt_303.findViewById(R.id.radioYa303)).setChecked(true);


        }

    }
}
