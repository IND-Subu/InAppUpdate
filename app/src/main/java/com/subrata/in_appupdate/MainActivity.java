package com.subrata.in_appupdate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        versionName = findViewById(R.id.versionName);
        versionName.setText("Hello_World");
        // Check for updates
        UpdateChecker.checkForUpdate(this);
    }
    public void showUpdateMessage(){
        versionName.setText("Update Available");
        Log.d("VersionName", "Available");
    }
}