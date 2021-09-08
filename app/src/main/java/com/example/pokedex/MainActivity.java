package com.example.pokedex;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pokedex.ui.main.SectionsPagerAdapter;
import com.example.pokedex.databinding.ActivityMainBinding;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends AppCompatActivity {

    Button buttonCrash;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        setTheme(R.style.Theme_Pokedex);
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        //Get version
        /*String version = "";
        int verCode = 0;
        try {
            PackageInfo pInfo = getBaseContext().getPackageManager().getPackageInfo(getBaseContext().getPackageName(), 0);
            version = pInfo.versionName;
            verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String code = String.valueOf(verCode);
        Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();*/



        //Microsoft App Center
        AppCenter.start(getApplication(), "{9e3b4607-4727-444b-b857-b706327e1bc5}",
                Analytics.class, Crashes.class);

        //Button to force a crash
        buttonCrash = findViewById(R.id.button_crash);
        buttonCrash.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Soy un crash de Pokedex"); // Force a crash
            }
        });
    }

}