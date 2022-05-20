package ru.twozeros.netology.mygame;

import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameUtils {
    public static void saveGame(String pathToSave, GameProgress gameProgress) {
        File file = new File(pathToSave);
        try (ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream(file))) {
            oot.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public static void zipFiles(String pathToZip, String... pathToFiles) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathToZip))) {
            for (String pathToFile : pathToFiles) {
                File file = new File(pathToFile);
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        deleteFiles(pathToFiles);
    }

    public static void deleteFiles(String... pathToFile) {
        Arrays.stream(pathToFile)
                .map(File::new)
                .forEach(File::delete);
    }
}
