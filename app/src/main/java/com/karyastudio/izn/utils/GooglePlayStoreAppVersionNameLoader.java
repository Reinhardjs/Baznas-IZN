package com.karyastudio.izn.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

import com.github.yeriomin.playstoreapi.ApiBuilderException;
import com.github.yeriomin.playstoreapi.DetailsResponse;
import com.github.yeriomin.playstoreapi.GooglePlayAPI;
import com.github.yeriomin.playstoreapi.PlayStoreApiBuilder;
import com.github.yeriomin.playstoreapi.PropertiesDeviceInfoProvider;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class GooglePlayStoreAppVersionNameLoader extends AsyncTask<String, Void, String> {

    String newVersion = "";
    String currentVersion = "";
    WSCallerVersionListener mWsCallerVersionListener;
    boolean isVersionAvailabel;
    boolean isAvailableInPlayStore;
    Context mContext;

    String mStringCheckUpdate = "";

    public GooglePlayStoreAppVersionNameLoader(Context mContext, WSCallerVersionListener callback) {
        mWsCallerVersionListener = callback;
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
        Utils.log("START DO IN BACKGROUND");
//        try {
//            isAvailableInPlayStore = true;
//            if (isNetworkAvailable(mContext)) {
//                mStringCheckUpdate = Jsoup.connect("https://play.google.com/store/apps/details?id=" + mContext.getPackageName())
//                        .timeout(10000)
//                        .get()
//                        .select("div[itemprop=softwareVersion]")
//                        .first()
//                        .ownText();
//                return mStringCheckUpdate;
//            }
//
//        } catch (Exception e) {
//            isAvailableInPlayStore = false;
//            return mStringCheckUpdate;
//        } catch (Throwable e) {
//            isAvailableInPlayStore = false;
//            return mStringCheckUpdate;
//        }

        checkApplicationCurrentVersion();


//        DetailsResponse response = null;
//        try {
//            response = getDetailResponse(BuildConfig.email,
//                    BuildConfig.password, "com.karyastudio.izn");
//
//            AppDetails appDetails = response.getDocV2().getDetails().getAppDetails();
//
//            Utils.log("new version name : " + appDetails.getVersionString());
//            Utils.log("new version code : " + appDetails.getVersionCode());
//
//            isAvailableInPlayStore = true;
//
//            return appDetails.getVersionString();
//
//        } catch (IOException e) {
//            Utils.log("IOEXCEPTION : " + e.toString());
//            e.printStackTrace();
//            isAvailableInPlayStore = false;
//        } catch (ApiBuilderException e) {
//            Utils.log("API BUILDER EXCEPTION : " + e.toString());
//            e.printStackTrace();
//            isAvailableInPlayStore = false;
//        }

        String newVersion = null;
        try {
            newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + mContext.getPackageName() + "&hl=it")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select(".hAyfc .htlgb")
                    .get(7)
                    .ownText();
            isAvailableInPlayStore = true;

            return newVersion;
        } catch (Exception e) {

            isAvailableInPlayStore = false;
            return newVersion;
        }
    }

    @Override
    protected void onPostExecute(String string) {
        if (isAvailableInPlayStore) {
            newVersion = string;
            // checkApplicationCurrentVersion();
            // Toast.makeText(mContext, "CURR VERSION : " + currentVersion + " \nNEW VERSION : " + newVersion, Toast.LENGTH_LONG).show();
            if (currentVersion.equalsIgnoreCase(newVersion)) {
                isVersionAvailabel = false;
                // Toast.makeText(mContext, mContext.getResources().getString(R.string.app_upto_date), Toast.LENGTH_LONG).show();
                // Toast.makeText(mContext, "UPDATE AVAILABLE", Toast.LENGTH_LONG).show();
            } else {
                isVersionAvailabel = true;
            }
            mWsCallerVersionListener.onGetResponse(isVersionAvailabel, newVersion);
        }
    }

    /**
     * Method to check current app version
     */
    public void checkApplicationCurrentVersion() {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        currentVersion = packageInfo.versionName;
        long currentCode = packageInfo.versionCode;
        Utils.log("currentVersion " + currentVersion);
        Utils.log("currentVersionCode " + currentCode + "");
    }

    private DetailsResponse getDetailResponse(String email,
                                              String password,
                                              String packageName) throws IOException, ApiBuilderException {
        // A device definition is required to log in
        // See resources for a list of available devices
        Properties properties = new Properties();
        try {
            properties.load(mContext.getAssets().open("device-honami.properties"));
        } catch (IOException e) {
            Utils.log("device-honami.properties not found");
            return null;
        }

        PropertiesDeviceInfoProvider deviceInfoProvider = new PropertiesDeviceInfoProvider();
        deviceInfoProvider.setProperties(properties);
        deviceInfoProvider.setLocaleString(Locale.ENGLISH.toString());

        // Provide valid google account info
        PlayStoreApiBuilder builder = new PlayStoreApiBuilder()
                .setDeviceInfoProvider(deviceInfoProvider)
                .setHttpClient(new OkHttpClientAdapter())
                .setEmail(email)
                .setPassword(password);
        GooglePlayAPI api = builder.build();

        // We are logged in now
        // Save and reuse the generated auth token and gsf id,
        // unless you want to get banned for frequent relogins
        api.getToken();
        api.getGsfId();

        // API wrapper instance is ready
        return api.details(packageName);
    }

    /**
     * Method to check internet connection
     * @param context
     * @return
     */
    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}