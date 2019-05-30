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
import android.widget.Toast;

import com.karyastudio.izn.R;
import com.karyastudio.izn.dao.generateSchema.KDZ;
import com.karyastudio.izn.model.api.datakdz.DataKDZ;
import com.karyastudio.izn.model.api.datakdz.KajianDampakZakatResponse;
import com.karyastudio.izn.model.api.kdz.KajianDampakZakat;
import com.karyastudio.izn.network.BaseApi;
import com.karyastudio.izn.utils.StaticStrings;
import com.karyastudio.izn.utils.Utils;
import com.karyastudio.izn.views.activities.SurveyKDZActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActionModeCallback implements ActionMode.Callback {

    private Context context;
    private AdapterDampakKajianZakat adapter;
    private ActionMode actionMode;

    public ActionModeCallback(Context context, AdapterDampakKajianZakat adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public void setActionMode(ActionMode actionMode){
        this.actionMode = actionMode;
    }

    public static ActionMode startActionMode(Activity activity, AdapterDampakKajianZakat adapter){
        ActionModeCallback callback = new ActionModeCallback(activity, adapter);
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
                        Call<DataKDZ> result = apiService.kdzItemData(StaticStrings.API_KEY, adapter.getArrayList().get(position).getFk_id());
                        Utils.log("BEGIN TO UPDATE FK ID : " + adapter.getArrayList().get(position).getFk_id());
                        result.enqueue(new Callback<DataKDZ>() {
                            @Override
                            public void onResponse(Call<DataKDZ> call, Response<DataKDZ> response) {
                                try {
                                    List<KajianDampakZakatResponse> list = response.body().getData().getKajianDampakZakat();

                                    Utils.log("Kabupaten : " + list.get(0).getFk_102_kabupaten());
                                    Utils.log("Provinsi : " + list.get(0).getFk_101_provinsi());
                                    Utils.log("ALAMAT : " + list.get(0).getFk_110_alamat_lengkap());

                                    Intent intent = new Intent(context, SurveyKDZActivity.class);
                                    intent.putExtra(SurveyKDZActivity.FORM_REQUEST_TYPE_DATA, SurveyKDZActivity.REQUEST_TYPE_UPDATE);
                                    intent.putExtra("data", list.get(0));
                                    context.startActivity(intent);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("tesapia->" + e);
                                }

                            }

                            @Override
                            public void onFailure(Call<DataKDZ> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });

                    }

                }

                Toast.makeText(context, "ACTION UPDATE CLICKED", Toast.LENGTH_SHORT).show();
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
                                    KDZ kdz = adapter.getArrayList().get(position);
                                    deleteKdz(kdz.getFk_id(), position);
                                }
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

    private void deleteKdz(String fk_id, int position){
        BaseApi apiService = Utils.initializeRetrofit().create(BaseApi.class);

        Utils.log("STARTING DELETE FOR FK_ID : " + fk_id + " AND POSITION : " + position);

        Call<KajianDampakZakat> result =
                apiService.deleteKdz(StaticStrings.API_KEY, fk_id);

        result.enqueue(new Callback<KajianDampakZakat>() {
            @Override
            public void onResponse(@NonNull Call<KajianDampakZakat> call, @NonNull Response<KajianDampakZakat> response) {
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {

                        Utils.log("Data Kajian Dampak Zakat berhasil dihapus");
                        Utils.log(response.body().getMessage() );
                        // Toast.makeText(context, "Data Kajian Dampak Zakat berhasil dihapus", Toast.LENGTH_LONG).show();

                        adapter.getArrayList().remove(position);
                        adapter.notifyDataSetChanged();
                        adapter.clearSelectedState();
                    } else {
                        // Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        Utils.log("RESPONSE :  " + response.body().getMessage());
                        Utils.log("Data Kajian Dampak Zakat tidak berhasil dihapus");
                    }
                } catch (Exception e) {
                    Utils.log(e.getMessage().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<KajianDampakZakat> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}