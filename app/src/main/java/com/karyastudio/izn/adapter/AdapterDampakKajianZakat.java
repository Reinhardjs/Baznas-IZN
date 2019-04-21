package com.karyastudio.izn.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.KDZ;

import java.util.List;

public class AdapterDampakKajianZakat  extends RecyclerView.Adapter<AdapterDampakKajianZakat.ViewHolder> {

    private List<KDZ> arrayList;

    public AdapterDampakKajianZakat(List<KDZ> arrayList){
        this.arrayList= arrayList;
    }

    @NonNull
    @Override
    public AdapterDampakKajianZakat.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDampakKajianZakat.ViewHolder viewHolder, int i) {
        viewHolder.setData(arrayList.get(i).getFk_id(),arrayList.get(i).getFk_date_created(),
                arrayList.get(i).getFk_date_updated(),arrayList.get(i).getCountKeluarga(),arrayList.get(i).getFk_nama());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private TextView id;
        private TextView date_created;
        private TextView date_updated;
        private TextView jenis_lembaga;
        private TextView counKel;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            jenis_lembaga = itemView.findViewById(R.id.fk_nama);
            date_created = itemView.findViewById(R.id.fk_date_created);
            date_updated = itemView.findViewById(R.id.fk_date_updated);
            counKel = itemView.findViewById(R.id.fk_nama2);
        }

        void setData(String fi_id, String fi_date_created, String fi_date_updated, String countKeluarga, String fi_jenis_lembaga){
            jenis_lembaga.setText(fi_jenis_lembaga);

            counKel.setText("jumlah keluarga : "+countKeluarga);
            date_updated.setText("Tanggal penyelesaian : \n"+fi_date_updated);
            date_created.setText("Tanggal memulai : \n"+fi_date_created);
        }
    }
}
