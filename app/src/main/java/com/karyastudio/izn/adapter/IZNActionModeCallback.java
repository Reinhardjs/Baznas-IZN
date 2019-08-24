package com.karyastudio.izn.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.IZN;
import com.karyastudio.izn.dao.managerSchema.IZNManager;
import com.karyastudio.izn.model.api.dataizn.IndeksZakatNasionalPojo;
import com.karyastudio.izn.model.api.izn.IndeksZakatNasional;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.SetGet;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyIZNActivity;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IZNActionModeCallback implements ActionMode.Callback {

    private Context context;
    private AdapterIndeksZakatNasional adapter;
    private ActionMode actionMode;

    public IZNActionModeCallback(Context context, AdapterIndeksZakatNasional adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public void setActionMode(ActionMode actionMode){
        this.actionMode = actionMode;
    }

    public static ActionMode startActionMode(Activity activity, AdapterIndeksZakatNasional adapter){
        IZNActionModeCallback callback = new IZNActionModeCallback(activity, adapter);
        ActionMode actionMode = ((AppCompatActivity) activity).startSupportActionMode(callback);
        callback.setActionMode(actionMode);
        return actionMode;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.toolbar_action_mode, menu);//Inflate the menu over action mode
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

        //Sometimes the meu will not be visible so for that we need to set their visibility manually in this method
        //So here show action menu according to SDK Levels
        if (Build.VERSION.SDK_INT < 11) {
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_edit), MenuItemCompat.SHOW_AS_ACTION_NEVER);
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_delete), MenuItemCompat.SHOW_AS_ACTION_NEVER);
            MenuItemCompat.setShowAsAction(menu.findItem(R.id.action_done), MenuItemCompat.SHOW_AS_ACTION_NEVER);
        } else {
            menu.findItem(R.id.action_edit).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_done).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Utils.log("Action Edit Clicked");
                if (adapter.getSelectedItemCount() > 1){
                    Utils.log("Selected item more than one");
                } else {
                    Utils.log("Selected item is just one");
                    BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);

                    for (int position : adapter.getSelectedItems()) {
                        IndeksZakatNasionalPojo izn = adapter.getArrayList().get(position);
                        String fi_id = izn.getFi_id();

                        List<IZN> data = IZNManager.loadByID(context, fi_id);

                        try {
                            Intent intent = new Intent(context, SurveyIZNActivity.class);
                            intent.putExtra(SurveyIZNActivity.FORM_REQUEST_TYPE_DATA, SurveyIZNActivity.REQUEST_TYPE_UPDATE);
                            intent.putExtra("data", SetGet.getResponseFromIzn(data.get(0)));
                            context.startActivity(intent);
                        } catch (Exception e){
                            // Ini ada exception krna bermasalah di putExtra data
                            // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
                            Utils.toast(context, "Mohon tunggu sebentar, sampai data benar-benar berhasil terkirim dan disimpan");
                        }
                    }

                }

                break;
            case R.id.action_delete:
                Utils.log("Action Delete Clicked");

                new AlertDialog.Builder(context)
                        .setTitle("Hapus Data")
                        .setMessage("Apakah Anda yakin ingin menghapus Data?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                for (int position : adapter.getSelectedItems()){
                                    IndeksZakatNasionalPojo izn = adapter.getArrayList().get(position);
                                    deleteIzn(izn.getFi_id(), position);
                                }

                                // Harus menggunakan looping iterator untuk menghapus data lebih dari satu
                                // Hal ini harus dilakukan kalau mau hapus data dengan iterasi
                                // Kalau ga, datanya bakalan berantakan
                                for(Iterator<IndeksZakatNasionalPojo> it = adapter.getArrayList().iterator(); it.hasNext();) {
                                    IndeksZakatNasionalPojo s = it.next();
                                    if(s == null) {
                                        it.remove();
                                    }
                                }

                                adapter.notifyDataSetChanged();
                                actionMode.finish();
                                adapter.notifyDataSetChanged();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("Tidak", null)
                        .setIcon(R.drawable.ic_action_delete_black)
                        .show();
                break;
            case R.id.action_done:
                actionMode.finish();
                break;
        }

        return true;
    }


    @Override
    public void onDestroyActionMode(ActionMode mode) {
        adapter.endChoiceMode();
        Utils.log("Action Mode Destroyed");
        //When action mode destroyed remove selected selections and set action mode to null
        //First check current fragment action mode
    }

    private void deleteIzn(String fi_id, int position){
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);

        adapter.getArrayList().remove(position);
        adapter.getArrayList().add(position, null);

        Utils.log("STARTING DELETE FOR FI_ID : " + fi_id + " AND POSITION : " + position);

        Call<IndeksZakatNasional> result =
                apiService.deleteIzn(StaticStrings.API_KEY, fi_id);

        result.enqueue(new Callback<IndeksZakatNasional>() {
            @Override
            public void onResponse(@NonNull Call<IndeksZakatNasional> call, @NonNull Response<IndeksZakatNasional> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {

                        Utils.log("Data Indeks Zakat Nasional berhasil dihapus");
                        Utils.log(response.body().getMessage());
                    } else {
                        Utils.log("RESPONSE :  " + response.body().getMessage());
                        Utils.log("Data Indeks Zakat Nasional tidak berhasil dihapus");
                    }
                } catch (Exception e) {
                    Utils.log(e.getMessage().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<IndeksZakatNasional> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}