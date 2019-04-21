package com.karyastudio.izn.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.IZN;

import java.util.List;

public class AdapterIndeksZakatNasional extends RecyclerView.Adapter<AdapterIndeksZakatNasional.ViewHolder> {

    private List<IZN> arrayList;

    public AdapterIndeksZakatNasional(List<IZN> arrayList){
        this.arrayList= arrayList;
    }

    @NonNull
    @Override
    public AdapterIndeksZakatNasional.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data,viewGroup,false);
        return new AdapterIndeksZakatNasional.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterIndeksZakatNasional.ViewHolder viewHolder, int i) {
        viewHolder.setData(arrayList.get(i).getFi_id(),arrayList.get(i).getFi_date_created(),
                arrayList.get(i).getFi_date_updated(),arrayList.get(i).getFi_code(),arrayList.get(i).getFi_jenis_lembaga());
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
        private TextView kode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            kode = itemView.findViewById(R.id.fk_nama2);
            jenis_lembaga = itemView.findViewById(R.id.fk_nama);
            date_created = itemView.findViewById(R.id.fk_date_created);
            date_updated = itemView.findViewById(R.id.fk_date_updated);
        }

        void setData(String fi_id, String fi_date_created, String fi_date_updated, String fi_code, String fi_jenis_lembaga){
            jenis_lembaga.setText(fi_jenis_lembaga);
            date_updated.setText("Tanggal penyelesaian : \n"+fi_date_updated);
            date_created.setText("Tanggal memulai : \n"+fi_date_created);
            kode.setText("Kode : "+fi_code);
        }
    }
}
