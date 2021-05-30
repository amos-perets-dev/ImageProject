package com.example.imageproject.manager.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PreferencesManagerImpl implements IPreferencesManager {

    private final String INDEX_IMAGE = "INDEX_IMAGE";
    private final String FILE_NAME = "image_project";

    private final SharedPreferences prefs;

    @Inject
    public PreferencesManagerImpl(Context context) {
        prefs = context.getSharedPreferences(
                "FILE_NAME", Context.MODE_PRIVATE);
    }

    @Override
    public @NotNull Integer getIndex() {
        return prefs.getInt(INDEX_IMAGE, 0);
    }

    @Override
    public void addIndex(int index) {
        prefs.edit().putInt(INDEX_IMAGE, index).apply();
    }
}
