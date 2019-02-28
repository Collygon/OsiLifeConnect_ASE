package com.example.osilifeconnect_ase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        configureUnitsButton();
        configureConfigurationButton();

    }

    private void configureUnitsButton() {
        Button unitsButton = (Button) findViewById(R.id.unitsButton);
        unitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, UnitsActivity.class));
            }
        });
    }

    private void configureConfigurationButton() {
        Button unitsButton = (Button) findViewById(R.id.unitsButton);
        unitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, ConfigurationActivity.class));
            }
        });
    }
}
