package com.example.imageproject.utils.files;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public interface IFilesUtil {
    /**
     * Unzip the inputStream
     *
     * @param inputStream
     * @return byte[] decompress
     */
    byte[] unzip(@NotNull InputStream inputStream) throws IOException;
}
