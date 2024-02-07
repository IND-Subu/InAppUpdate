package com.subrata.in_appupdate;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateChecker {

    private static final String GITHUB_API_BASE_URL = "https://api.github.com/repos/IND-Subu/Vehicle/";

    public static void checkForUpdate(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<Release> call = service.getLatestRelease();

        call.enqueue(new Callback<Release>() {
            @Override
            public void onResponse(Call<Release> call, Response<Release> response) {
                if (response.isSuccessful()) {
                    Release latestRelease = response.body();
                    if (latestRelease != null) {
                        String latestVersion = latestRelease.getTagName();
                        String currentVersion = BuildConfig.VERSION_NAME;
                        if (!latestVersion.equals(currentVersion)) {
                            showUpdateDialog(context);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Release> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private static void showUpdateDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update Available")
                .setMessage("A new version of the app is available. Do you want to update?")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Implement update logic, e.g., open a browser to download the new APK
                        // You need to handle the download and installation logic here
                        if (context instanceof MainActivity) {
                            ((MainActivity) context).showUpdateMessage();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User chose not to update
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
