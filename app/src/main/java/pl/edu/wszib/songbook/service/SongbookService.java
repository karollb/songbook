package pl.edu.wszib.songbook.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class SongbookService {
    public static ArrayList<String> getFilesList(File dir) {
        ArrayList<String> arrayList = new ArrayList<>();

        File[] files = dir.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            arrayList.add(files[i].getName());
        }
        return arrayList;
    }
}
