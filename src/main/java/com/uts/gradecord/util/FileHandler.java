package com.uts.gradecord.util;

import com.uts.gradecord.model.Siswa;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "siswa.csv";

    private FileHandler() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Siswa> loadSiswa() {
        List<Siswa> list = new ArrayList<>();
        try {
            Path path = Paths.get(FILE_NAME);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    list.add(new Siswa(parts[0], parts[1],
                            Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveSiswa(List<Siswa> siswaList) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            for (Siswa siswa : siswaList) {
                writer.write(String.join(",",
                        siswa.getNim(),
                        siswa.getNama(),
                        String.valueOf(siswa.getMatematika()),
                        String.valueOf(siswa.getIpa()),
                        String.valueOf(siswa.getIndonesia())));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
