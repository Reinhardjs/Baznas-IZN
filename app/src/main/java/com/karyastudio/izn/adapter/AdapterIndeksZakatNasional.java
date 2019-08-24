package com.karyastudio.izn.adapter;

import android.arch.lifecycle.Observer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.karyastudio.izn.R;
import com.karyastudio.izn.model.api.dataizn.IndeksZakatNasionalPojo;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.fragments.modul2.FragmentDataModul2;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdapterIndeksZakatNasional extends RecyclerView.Adapter<AdapterIndeksZakatNasional.ViewHolder> {

    private List<IndeksZakatNasionalPojo> arrayList;

    private SparseBooleanArray mSelectedItems = new SparseBooleanArray();
    private boolean mIsInChoiceMode = false;
    private AdapterIndeksZakatNasional.ClickListener mClickListener;

    public AdapterIndeksZakatNasional(List<IndeksZakatNasionalPojo> arrayList, ClickListener clickListener){
        this.arrayList= arrayList;
        mClickListener = clickListener;
    }

    public List<IndeksZakatNasionalPojo> getArrayList() {
        return arrayList;
    }

    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    public void switchSelectedState(int position) {
        if (mSelectedItems.get(position)) {       // item has been selected, de-select it.
            mSelectedItems.delete(position);
        } else {
            mSelectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    public void clearSelectedState() {
        List<Integer> selection = getSelectedItems();
        mSelectedItems.clear();
        for (Integer i : selection) {
            notifyDataSetChanged();
        }
    }

    public int getSelectedItemCount() {
        return mSelectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(mSelectedItems.size());
        for (int i = 0; i < mSelectedItems.size(); ++i) {
            items.add(mSelectedItems.keyAt(i));
        }
        return items;
    }

    public void setIsInChoiceMode(boolean isInChoiceMode) {
        this.mIsInChoiceMode = isInChoiceMode;
    }

    public boolean getIsInChoiceMode() {
        return mIsInChoiceMode;
    }

    public void beginChoiceMode(int position){
        setIsInChoiceMode(true);
        switchSelectedState(position);
        notifyDataSetChanged();
    }

    public void endChoiceMode(){
        clearSelectedState();
        setIsInChoiceMode(false);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterIndeksZakatNasional.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data,viewGroup,false);
        return new AdapterIndeksZakatNasional.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterIndeksZakatNasional.ViewHolder viewHolder, int position) {
        viewHolder.setData(arrayList.get(position).getFi_id(),arrayList.get(position).getFi_date_created(),
                arrayList.get(position).getFi_date_updated(),arrayList.get(position).getFi_code(),arrayList.get(position).getFi_101_jenis_lembaga());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClicked(position);
            }
        });

        viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mClickListener.onItemLongClicked(position);
            }
        });

        if (mIsInChoiceMode) {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
            viewHolder.checkBox.setChecked(mSelectedItems.get(position));
        } else {
            viewHolder.checkBox.setChecked(false);      // clear all checkbox if we want.
            viewHolder.checkBox.setVisibility(View.GONE);
        }

        if (arrayList.get(position).getStatus().equals(StaticStrings.IZN_STATUS_PENDING)){
            viewHolder.status.setImageResource(R.drawable.ic_info_black_24dp);
            viewHolder.status.setColorFilter(ContextCompat.getColor(FragmentDataModul2.context, R.color.colorAccent), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            viewHolder.status.setImageResource(R.drawable.ic_check_black);
            viewHolder.status.setColorFilter(ContextCompat.getColor(FragmentDataModul2.context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        Disposable networkDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> Utils.log("ERROR RX NETWORK : " + error.toString()))
                .subscribe(isConnected -> {

                    if (isConnected){
                        Utils.log("RX JAVA CONNECTED TO INTERNET");
                        Utils.log("DATA POS ("+ position +"), TYPE : " + arrayList.get(position).getRequest_type());

                        if (arrayList.get(position).getStatus().equals(StaticStrings.KDZ_STATUS_PENDING) && !arrayList.get(position).isSending){

                            arrayList.get(position).isSending = true;
                            viewHolder.progressBar.setVisibility(View.VISIBLE);
                            viewHolder.status.setVisibility(View.GONE);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Utils.saveAndNext(FragmentDataModul2.context, arrayList.get(position));
                                }
                            }, 2000);
                        }

                    }

                }, error -> Utils.log("ERROR RX NETWORK : " + error.getMessage()));

        arrayList.get(position).getLiveStatus().observe(FragmentDataModul2.getInstance(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String status) {
                Utils.log(AdapterDampakKajianZakat.class, "CHANGED : " + status);

                if (status.equals(StaticStrings.KDZ_STATUS_SENT)){
                    viewHolder.progressBar.setVisibility(View.GONE);
                    viewHolder.status.setImageResource(R.drawable.ic_check_black);
                    viewHolder.status.setColorFilter(ContextCompat.getColor(FragmentDataModul2.context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
                    viewHolder.status.setVisibility(View.VISIBLE);

                    // Untuk menghindari java.lang.IndexOutOfBoundsException: Invalid index 0,
                    // Saat repopulate. Karna livedata bisa dipanggil saat perubahan,
                    // sedangkan datanya(list) udah ganti pointer
                    if (position < arrayList.size())
                    arrayList.get(position).isSending = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private CheckBox checkBox;
        private TextView id;
        private TextView date_created;
        private TextView date_updated;
        private TextView jenis_lembaga;
        private TextView kode;
        private ImageView status;
        private ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            kode = itemView.findViewById(R.id.fk_nama2);
            checkBox = itemView.findViewById(R.id.checkBox);
            jenis_lembaga = itemView.findViewById(R.id.fk_nama);
            date_created = itemView.findViewById(R.id.fk_date_created);
            date_updated = itemView.findViewById(R.id.fk_date_updated);
            status = itemView.findViewById(R.id.status);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        void setData(String fi_id, String fi_date_created, String fi_date_updated, String fi_code, String fi_jenis_lembaga){
            jenis_lembaga.setText(fi_jenis_lembaga);
            date_updated.setText("Tanggal penyelesaian : \n"+fi_date_updated);
            date_created.setText("Tanggal memulai : \n"+fi_date_created);
            kode.setText("Kode : \n"+fi_code);
        }
    }

    public interface ClickListener {
        void onItemClicked(int position);
        boolean onItemLongClicked(int position);
    }
}
