package com.example.android.toyapp.activity.visualizerpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.android.toyapp.R;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

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

        findPreference(getString(R.string.pref_size_key)).setOnPreferenceChangeListener(this);
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

    /**
     * triggered after data is changed but before saved on SharedPreferences
     * @param preference
     * @param newValue
     * @return true only if save new data on SharedPreferences
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final Toast error = Toast.makeText(getContext(), getString(R.string.error_size,"0.1","3"), Toast.LENGTH_SHORT);

        final String sizeKey = getString(R.string.pref_size_key);
        if (preference.getKey().equals(sizeKey)) {
            final String stringSize = (String) newValue;
            try {
                float size = Float.parseFloat(stringSize);
                if (size > 3 || size <= 0) {
                    error.show();
                    return false;
                }
            } catch (NumberFormatException nfe) {
                error.show();
                return false;
            }
        }
        return true;
    }

    /**
     * triggered after data is changed and saved on SharedPreferences
     * @param sharedPreferences
     * @param key
     */
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
