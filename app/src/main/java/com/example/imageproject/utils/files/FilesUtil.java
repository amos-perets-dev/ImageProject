package com.example.imageproject.utils.files;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesUtil implements IFilesUtil {
    public byte[] unzip(@NotNull InputStream inputStream) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry zipEntry;
            int count;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {

                if (zipEntry.isDirectory()) {
                    continue;
                }

                byte[] size = new byte[(int) zipEntry.getSize()];

                try {
                    while ((count = zipInputStream.read(size)) != -1) {
                        os.write(size, 0, count);
                    }
                } catch (Exception ignored) {
                }

                break;
            }

            return os.toByteArray();
        }
    }
}
