package com.karyastudio.izn.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.KDZ;

import java.util.ArrayList;
import java.util.List;

public class AdapterDampakKajianZakat  extends RecyclerView.Adapter<AdapterDampakKajianZakat.ViewHolder> {

    private List<KDZ> arrayList;

    private SparseBooleanArray mSelectedItems = new SparseBooleanArray();
    private boolean mIsInChoiceMode = false;
    private AdapterDampakKajianZakat.ClickListener mClickListener;

    public AdapterDampakKajianZakat(List<KDZ> arrayList, ClickListener clickListener){
        this.arrayList= arrayList;
        mClickListener = clickListener;
    }

    public List<KDZ> getArrayList() {
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
    public AdapterDampakKajianZakat.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDampakKajianZakat.ViewHolder viewHolder, int position) {

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

        viewHolder.setData(arrayList.get(position).getFk_id(),arrayList.get(position).getFk_date_created(),
                arrayList.get(position).getFk_date_updated(),arrayList.get(position).getCountKeluarga(),arrayList.get(position).getFk_nama());

        if (mIsInChoiceMode) {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
            viewHolder.checkBox.setChecked(mSelectedItems.get(position));
        } else {
            viewHolder.checkBox.setChecked(false);      // clear all checkbox if we want.
            viewHolder.checkBox.setVisibility(View.GONE);
        }
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
        private TextView counKel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            checkBox = itemView.findViewById(R.id.checkBox);
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

    public interface ClickListener {
        void onItemClicked(int position);
        boolean onItemLongClicked(int position);
    }
}
