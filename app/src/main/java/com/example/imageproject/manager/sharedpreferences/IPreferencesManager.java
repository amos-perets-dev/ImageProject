package com.example.imageproject.manager.sharedpreferences;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Completable;

public interface IPreferencesManager {

    /**
     * Get the curr index
     *
     * @return curr index
     */
    @NotNull Integer getIndex();

    /**
     * Add new index
     *
     * @param index new index
     */
    void addIndex(int index);
}
