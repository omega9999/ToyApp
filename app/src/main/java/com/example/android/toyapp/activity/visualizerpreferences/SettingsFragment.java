package com.example.android.toyapp.activity.visualizerpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.android.toyapp.R;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        // Add visualizer preferences, defined in the XML file in res->xml->pref_visualizer
        addPreferencesFromResource(R.xml.pref_visualizer);

        final SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        for (int index = 0; index < getPreferenceScreen().getPreferenceCount(); index++) {
            final Preference preference = getPreferenceScreen().getPreference(index);
            if (!(preference instanceof CheckBoxPreference)){
                final String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(@NonNull final SharedPreferences sharedPreferences, @NonNull final String key) {
        final Preference preference = findPreference(key);
        if (null != preference) {
            // Updates the summary for the preference
            if (!(preference instanceof CheckBoxPreference)) {
                final String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
            }
        }
    }

    private void setPreferenceSummary(@NonNull final Preference preference, @Nullable final String value){
        if (preference instanceof ListPreference){
            final ListPreference listPreference = (ListPreference)preference;
            final int index = listPreference.findIndexOfValue(value);
            if (index >= 0){
                preference.setSummary(listPreference.getEntries()[index]);
            }
        }
        else if (preference instanceof EditTextPreference){
            preference.setSummary(value);
        }
    }






}
