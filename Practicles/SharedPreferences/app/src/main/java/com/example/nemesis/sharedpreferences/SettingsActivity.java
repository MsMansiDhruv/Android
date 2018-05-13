package com.example.nemesis.sharedpreferences;

import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,new SettingsFragment()).commit();

        Toast.makeText(this, "Settings Activity", Toast.LENGTH_SHORT).show();
    }

    public static class SettingsFragment extends PreferenceFragment{

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings);

            Toast.makeText(getActivity(), "Settings Fragment", Toast.LENGTH_SHORT).show();
        }
    }
}
