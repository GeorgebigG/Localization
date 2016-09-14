package com.rustaronline.mobile.localizationtest;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner languageSpinner;
    ArrayAdapter<CharSequence> adapter;
    TextView text;

    boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = ArrayAdapter.createFromResource(this, R.array.languages, R.layout.support_simple_spinner_dropdown_item);
        languageSpinner = (Spinner) findViewById(R.id.languageS);
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(this);

        text = (TextView) findViewById(R.id.textView);
        text.setText(getResources().getString(R.string.name));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (isFirstTime) {
            isFirstTime = false;
            return;
        }

        setLocale(languageSpinner.getSelectedItem().toString());
        text.setText(getResources().getString(R.string.name));
        Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private void setLocale(String lang) {
        if (lang.equals("English"))
            lang = "";

        else if (lang.equals("Русский"))
            lang = "ru";

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        Locale myLocale = new Locale(lang);
        conf.locale = myLocale;

        res.updateConfiguration(conf, dm);
    }
}
